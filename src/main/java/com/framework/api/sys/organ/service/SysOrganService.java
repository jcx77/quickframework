package com.framework.api.sys.organ.service;


import com.framework.api.sys.organ.mapper.SysOrganMapper;
import com.framework.api.sys.organ.model.SysOrgan;
import com.framework.api.sys.user.mapper.SysUserMapper;
import com.framework.api.sys.user.model.SysUser;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.exception.BizException;
import com.framework.commons.vo.ui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysOrganService {
	@Autowired
	private SysOrganMapper sysOrganMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	@Transactional(readOnly = true)
	public List<SysOrgan> find(String deleteFlag) {
		Example example = new Example(SysOrgan.class);
		example.orderBy("code");
		Criteria criteria = example.createCriteria();
		if (deleteFlag != null) {
			criteria.andEqualTo("deleteFlag", deleteFlag);
		}
		return sysOrganMapper.selectByExample(example);
	}

	@Transactional(readOnly = true)
	public SysOrgan get(String id) {
		return sysOrganMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void insert(SysOrgan sysOrgan) {
		sysOrganMapper.insertSelective(sysOrgan);
	}

	@CacheEvict(cacheNames = "shiro", allEntries = true)
	@Transactional
	public void update(SysOrgan sysOrgan) {
		List<String> ids = new ArrayList<String>();
		children(sysOrgan.getId(), ids);
		if (ids.contains(sysOrgan.getPid())) {
			throw new BizException("不能选择当前机构以及子机构");
		}
		sysOrganMapper.updateByPrimaryKeySelective(sysOrgan);
	}

	@CacheEvict(cacheNames = "shiro", allEntries = true)
	@Transactional
	public void delete(String id) {
		SysOrgan sysOrgan = sysOrganMapper.selectByPrimaryKey(id);
		if ("organ".equals(id)) {
			throw new BizException("不允许删除总机构");
		}
		{
			Example example = new Example(SysOrgan.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("pid", id);
			criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
			if (sysOrganMapper.selectCountByExample(example) > 0) {
				throw new BizException("存在下级机构，不允许删除");
			}
		}
		{
			Example example = new Example(SysUser.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("organId", id);
			criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
			if (sysUserMapper.selectCountByExample(example) > 0) {
				throw new BizException("存在用户，不允许删除");
			}
		}
		sysOrgan.setDeleteFlag(DataConstant.DELETE_FLAG_1);
		sysOrganMapper.updateByPrimaryKeySelective(sysOrgan);
	}

	@Transactional
	public void recovery(String id) {
		SysOrgan sysOrgan = sysOrganMapper.selectByPrimaryKey(id);
		Example example = new Example(SysOrgan.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", sysOrgan.getPid());
		criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_1);
		if (sysOrganMapper.selectCountByExample(example) > 0) {
			throw new BizException("先还原上级机构");
		}
		sysOrgan.setDeleteFlag(DataConstant.DELETE_FLAG_0);
		sysOrganMapper.updateByPrimaryKeySelective(sysOrgan);
	}

	@Transactional(readOnly = true)
	public List<TreeNode> findTree(String deleteFlag) {
		return sysOrganMapper.findTree(deleteFlag);
	}

	private void children(String id, List<String> ids) {
		ids.add(id);
		SysOrgan record = new SysOrgan();
		record.setPid(id);
		List<SysOrgan> list = sysOrganMapper.select(record);
		for (SysOrgan sysOrgan : list) {
			children(sysOrgan.getId(), ids);
		}
	}
}