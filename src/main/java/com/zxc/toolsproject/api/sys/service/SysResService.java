package com.zxc.toolsproject.api.sys.service;

import java.util.ArrayList;
import java.util.List;


import com.zxc.toolsproject.api.sys.mapper.SysResMapper;
import com.zxc.toolsproject.api.sys.mapper.SysRoleResMapper;
import com.zxc.toolsproject.api.sys.mapper.SysUserResMapper;
import com.zxc.toolsproject.api.sys.model.SysRes;
import com.zxc.toolsproject.api.sys.model.SysRoleRes;
import com.zxc.toolsproject.api.sys.model.SysUserRes;
import com.zxc.toolsproject.commons.constant.DataConstant;
import com.zxc.toolsproject.commons.exception.BizException;
import com.zxc.toolsproject.commons.vo.ui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.util.StrUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SysResService {
	@Autowired
	private SysResMapper sysResMapper;
	@Autowired
	private SysRoleResMapper sysRoleResMapper;
	@Autowired
	private SysUserResMapper sysUserResMapper;

	@Transactional(readOnly = true)
	public List<SysRes> find() {
		Example example = new Example(SysRes.class);
		example.orderBy("sort");
		return sysResMapper.selectByExample(example);
	}

	@Transactional(readOnly = true)
	public SysRes get(String id) {
		return sysResMapper.selectByPrimaryKey(id);
	}

	@CacheEvict(cacheNames = "shiro", allEntries = true)
	@Transactional
	public void insert(SysRes sysRes) {
		if (sysResMapper.existsWithPrimaryKey(sysRes.getId())) {
			throw new BizException("资源代码不允许重复");
		}
		if (StrUtil.isEmpty(sysRes.getPid())) {
			sysRes.setPid(DataConstant.DEFAULT_PID);
		}
		sysResMapper.insertSelective(sysRes);
	}

	@CacheEvict(cacheNames = "shiro", allEntries = true)
	@Transactional
	public void update(SysRes sysRes) {
		if (StrUtil.isEmpty(sysRes.getPid())) {
			sysRes.setPid(DataConstant.DEFAULT_PID);
		}
		List<String> ids = new ArrayList<String>();
		children(sysRes.getId(), ids);
		if (ids.contains(sysRes.getPid())) {
			throw new BizException("不能选择当前资源以及子资源");
		}
		sysResMapper.updateByPrimaryKeySelective(sysRes);
	}

	@CacheEvict(cacheNames = "shiro", allEntries = true)
	@Transactional
	public void delete(String id) {
		Example example = new Example(SysRes.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("pid", id);
		if (sysResMapper.selectCountByExample(example) > 0) {
			throw new BizException("存在子资源，不允许删除");
		}
		sysResMapper.deleteByPrimaryKey(id);
		SysRoleRes sysRoleRes = new SysRoleRes();
		sysRoleRes.setResId(id);
		sysRoleResMapper.delete(sysRoleRes);
		SysUserRes sysUserRes = new SysUserRes();
		sysUserRes.setResId(id);
		sysUserResMapper.delete(sysUserRes);
	}

	@Transactional(readOnly = true)
	public List<TreeNode> findTree() {
		return sysResMapper.findTree();
	}

	private void children(String id, List<String> ids) {
		ids.add(id);
		SysRes record = new SysRes();
		record.setPid(id);
		List<SysRes> list = sysResMapper.select(record);
		for (SysRes sysRes : list) {
			children(sysRes.getId(), ids);
		}
	}
}