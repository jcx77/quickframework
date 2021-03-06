package com.framework.api.sys.job.service;

import cn.hutool.core.util.StrUtil;
import com.framework.api.sys.job.mapper.SysJobMapper;
import com.framework.api.sys.job.model.SysJob;
import com.framework.api.sys.job.vo.SysJobParam;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.exception.BizException;
import com.framework.commons.quartz.QuartzJob;
import org.quartz.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Service
public class SysJobService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysJobMapper sysJobMapper;
	@Autowired
	private Scheduler scheduler;

	@Transactional(readOnly = true)
	public List<SysJob> find(SysJobParam sysJobParam) {
		Example example = new Example(SysJob.class);
		example.orderBy("name");
		Criteria criteria = example.createCriteria();
		if (StrUtil.isNotEmpty(sysJobParam.getStatus())) {
			criteria.andEqualTo("status", sysJobParam.getStatus());
		}
		if (sysJobParam.getDeleteFlag() != null) {
			criteria.andEqualTo("deleteFlag", sysJobParam.getDeleteFlag());
		}
		return sysJobMapper.selectByExample(example);
	}

	@Transactional(readOnly = true)
	public SysJob get(String id) {
		return sysJobMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void insert(SysJob sysJob) {
		if (!isJobClass(sysJob.getName())) {
			throw new BizException("任务类名必须继承QuartzJob");
		}
		if (!CronExpression.isValidExpression(sysJob.getCron())) {
			throw new BizException("时间表达式无效");
		}
		sysJob.setStatus(DataConstant.JOB_STATUS_1);
		sysJobMapper.insertSelective(sysJob);
	}

	@Transactional
	public void update(SysJob sysJob) {
		SysJob record = new SysJob();
		record.setId(sysJob.getId());
		record.setStatus(DataConstant.JOB_STATUS_0);
		if (sysJobMapper.selectCount(record) > 0) {
			throw new BizException("任务已启动，不允许修改");
		}
		if (!CronExpression.isValidExpression(sysJob.getCron())) {
			throw new BizException("时间表达式无效");
		}
		sysJobMapper.updateByPrimaryKeySelective(sysJob);
	}

	@Transactional
	public void delete(String id) {
		SysJob record = new SysJob();
		record.setId(id);
		record.setStatus(DataConstant.JOB_STATUS_0);
		if (sysJobMapper.selectCount(record) > 0) {
			throw new BizException("任务已启动，不允许删除");
		}
		SysJob sysJob = new SysJob();
		sysJob.setId(id);
		sysJob.setDeleteFlag(DataConstant.DELETE_FLAG_1);
		sysJobMapper.updateByPrimaryKeySelective(sysJob);
	}

	@Transactional
	public void recovery(String id) {
		SysJob sysJob = new SysJob();
		sysJob.setId(id);
		sysJob.setDeleteFlag(DataConstant.DELETE_FLAG_0);
		sysJobMapper.updateByPrimaryKeySelective(sysJob);
	}

	@Transactional
	public void start(String id) {
		SysJob sysJob = get(id);
		if (sysJob.getDeleteFlag().equals(DataConstant.DELETE_FLAG_1)) {
			throw new BizException("任务已删除，不允许启动");
		}
		if (sysJob.getStatus().equals(DataConstant.JOB_STATUS_0)) {
			throw new BizException("任务已启动，不允许重复启动");
		}
		sysJob.setStatus(DataConstant.JOB_STATUS_0);
		sysJobMapper.updateByPrimaryKeySelective(sysJob);
		if (!addJob(sysJob)) {
			throw new BizException("任务启动失败");
		}
	}

	@Transactional
	public void stop(String id) {
		SysJob sysJob = get(id);
		if (sysJob.getDeleteFlag().equals(DataConstant.DELETE_FLAG_1)) {
			throw new BizException("任务已删除，不允许停止");
		}
		if (sysJob.getStatus().equals(DataConstant.JOB_STATUS_1)) {
			throw new BizException("任务已停止，不允许重复停止");
		}
		sysJob.setStatus(DataConstant.JOB_STATUS_1);
		sysJobMapper.updateByPrimaryKeySelective(sysJob);
		if (!deleteJob(id)) {
			throw new BizException("任务停止失败");
		}
	}

	@Transactional(readOnly = true)
	public void init() {
		SysJobParam sysJobParam = new SysJobParam();
		sysJobParam.setStatus(DataConstant.JOB_STATUS_0);
		sysJobParam.setDeleteFlag(DataConstant.DELETE_FLAG_0);
		List<SysJob> list = find(sysJobParam);
		for (SysJob sysJob : list) {
			if (!addJob(sysJob)) {
				throw new BizException("任务启动失败");
			}
		}
	}

	private boolean addJob(SysJob sysJob) {
		try {
			if (!scheduler.checkExists(JobKey.jobKey(sysJob.getId()))) {
				JobDetail jobDetail = JobBuilder.newJob(getClass(sysJob.getName())).withIdentity(sysJob.getId()).withDescription(sysJob.getDesc()).usingJobData("param", sysJob.getParam()).requestRecovery(true).storeDurably(true).build();
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCron());
				CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(sysJob.getId()).withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();
				scheduler.scheduleJob(jobDetail, trigger);
			}
			return true;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private boolean deleteJob(String id) {
		try {
			scheduler.deleteJob(JobKey.jobKey(id));
			return true;
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Job> getClass(String className) throws ClassNotFoundException {
		return (Class<? extends Job>) Class.forName(className);
	}

	private boolean isJobClass(String className) {
		try {
			return QuartzJob.class.isAssignableFrom(Class.forName(className));
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
}