package com.framework.api.core.service;

import com.framework.api.core.vo.ui.Param;
import com.framework.api.sys.dict.mapper.SysDictItemMapper;
import com.framework.api.sys.dict.mapper.SysDictMapper;
import com.framework.api.sys.dict.model.SysDict;
import com.framework.api.sys.dict.model.SysDictItem;
import com.framework.api.sys.param.mapper.SysParamMapper;
import com.framework.api.sys.param.model.SysParam;
import com.framework.api.sys.user.mapper.SysUserMapper;
import com.framework.api.sys.user.model.SysUser;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.exception.BizException;
import com.framework.commons.shiro.tools.ShiroTools;
import com.framework.commons.vo.ui.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.beans.PropertyDescriptor;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoreService {
	@Autowired
	private SysUserMapper sysUserMapper;
//	@Autowired
//	private SysOrganMapper sysOrganMapper;
//	@Autowired
//	private SysUserOrganMapper sysUserOrganMapper;
	@Autowired
	private SysParamMapper sysParamMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private SysDictItemMapper sysDictItemMapper;

	@Cacheable(cacheNames = "shiro", key = "'param'")
	@Transactional(readOnly = true)
	public Param getParam() {
		Param param = new Param();
		try {
			List<SysParam> list = sysParamMapper.selectAll();
			for (SysParam sysParam : list) {
				new PropertyDescriptor(sysParam.getCode(), Param.class).getWriteMethod().invoke(param, sysParam.getValue());
			}
		} catch (Exception e) {
			return null;
		}
		return param;
	}

	@Transactional(readOnly = true)
	public SysUser getSysUserByAccount(String account) {
		SysUser record = new SysUser();
		record.setAccount(account);
		record.setDeleteFlag(DataConstant.DELETE_FLAG_0);
		return sysUserMapper.selectOne(record);
	}
//
//	@Transactional(readOnly = true)
//	public SysOrgan getSysOrgan(String id) {
//		SysOrgan record = new SysOrgan();
//		record.setId(id);
//		record.setDeleteFlag(DataConstant.DELETE_FLAG_0);
//		return sysOrganMapper.selectOne(record);
//	}
//
	@CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#id")
	@Transactional
	public String updatePassword(String id, String password) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		sysUser.setPassword(ShiroTools.md5(password, id));
		//sysUserMapper.updateByPrimaryKeySelective(sysUser);
		return sysUser.getPassword();
	}
//
//	@Transactional(readOnly = true)
//	public List<SysOrgan> findOrgan(String userId) {
//		SysUserOrgan record = new SysUserOrgan();
//		record.setUserId(userId);
//		List<SysUserOrgan> list = sysUserOrganMapper.select(record);
//		List<String> ids = new ArrayList<String>();
//		for (SysUserOrgan sysUserOrgan : list) {
//			ids.add(sysUserOrgan.getOrganId());
//		}
//		Example example = new Example(SysOrgan.class);
//		example.orderBy("code");
//		Criteria criteria = example.createCriteria();
//		criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
//		criteria.andIn("id", ids);
//		return sysOrganMapper.selectByExample(example);
//	}
//
//	@CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#id")
//	@Transactional
//	public void updateOrgan(String id, String organId) {
//		{
//			SysOrgan record = new SysOrgan();
//			record.setId(organId);
//			record.setDeleteFlag(DataConstant.DELETE_FLAG_0);
//			if (sysOrganMapper.selectCount(record) == 0) {
//				throw new BizException("机构已删除");
//			}
//		}
//		{
//			SysUserOrgan record = new SysUserOrgan();
//			record.setUserId(id);
//			record.setOrganId(organId);
//			if (sysUserOrganMapper.selectCount(record) == 0) {
//				throw new BizException("未关联机构");
//			}
//		}
//		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
//		sysUser.setOrganId(organId);
//		sysUserMapper.updateByPrimaryKeySelective(sysUser);
//	}
//
	@Cacheable(cacheNames = "dict", key = "'dict_option_'+#dictId")
	@Transactional(readOnly = true)
	public List<Option> findOption(String dictId) {
		return findOption(dictId, DataConstant.DELETE_FLAG_0);
	}

	@Cacheable(cacheNames = "dict", key = "'dict_option_all_'+#dictId")
	@Transactional(readOnly = true)
	public List<Option> findOptionAll(String dictId) {
		return findOption(dictId, null);
	}

	private List<Option> findOption(String dictId, String deleteFlag) {
		SysDict record = new SysDict();
		record.setId(dictId);
		record.setDeleteFlag(DataConstant.DELETE_FLAG_1);
		if (sysDictMapper.selectCount(record) > 0) {
			throw new BizException("字典已删除");
		}
		List<Option> list = new ArrayList<Option>();
		Example example = new Example(SysDictItem.class);
		example.orderBy("sort");
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("dictId", dictId);
		if (deleteFlag != null) {
			criteria.andEqualTo("deleteFlag", deleteFlag);
		}
		List<SysDictItem> sysDictItems = sysDictItemMapper.selectByExample(example);
		for (SysDictItem sysDictItem : sysDictItems) {
			list.add(new Option(sysDictItem.getCode(), sysDictItem.getName()));
		}
		return list;
	}
}