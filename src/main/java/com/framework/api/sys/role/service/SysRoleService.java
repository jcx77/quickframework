package com.framework.api.sys.role.service;

import cn.hutool.core.util.StrUtil;
import com.framework.api.sys.role.model.SysRole;
import com.framework.api.sys.role.mapper.SysRoleResMapper;
import com.framework.api.sys.role.mapper.SysRoleMapper;
import com.framework.api.sys.role.model.SysRoleRes;
import com.framework.api.sys.vo.param.SysRoleResParam;
import com.framework.commons.vo.ui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.framework.commons.exception.BizException;
import com.framework.commons.constant.DataConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleResMapper sysRoleResMapper;

    @Transactional(readOnly = true)
    public List<SysRole> find(String deleteFlag) {
        Example example = new Example(SysRole.class);
        example.orderBy("code");
        Example.Criteria criteria = example.createCriteria();
        if (deleteFlag != null) {
            criteria.andEqualTo("deleteFlag", deleteFlag);
        }
        return sysRoleMapper.selectByExample(example);
    }

    @Transactional(readOnly = true)
    public SysRole get(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void insert(SysRole sysRole) {
        if (StrUtil.isEmpty(sysRole.getPid())) {
            sysRole.setPid(DataConstant.DEFAULT_PID);
        }
        sysRoleMapper.insertSelective(sysRole);
    }

    @Transactional
    public void update(SysRole sysRole) {
        if (StrUtil.isEmpty(sysRole.getPid())) {
            sysRole.setPid(DataConstant.DEFAULT_PID);
        }
        List<String> ids = new ArrayList<String>();
        children(sysRole.getId(), ids);
        if (ids.contains(sysRole.getPid())) {
            throw new BizException("不能选择当前角色以及子角色");
        }
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @CacheEvict(cacheNames = "shiro", allEntries = true)
    @Transactional
    public void delete(String id) {
        {
            Example example = new Example(SysRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("pid", id);
            criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
            if (sysRoleMapper.selectCountByExample(example) > 0) {
                throw new BizException("存在下级角色，不允许删除");
            }
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @CacheEvict(cacheNames = "shiro", allEntries = true)
    @Transactional
    public void recovery(String id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sysRole.getPid());
        criteria.andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_1);
        if (sysRoleMapper.selectCountByExample(example) > 0) {
            throw new BizException("先还原上级角色");
        }
        sysRole.setDeleteFlag(DataConstant.DELETE_FLAG_0);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Transactional(readOnly = true)
    public List<TreeNode> findResTreeById(String id) {
        return sysRoleMapper.findResTreeById(id);
    }

    @CacheEvict(cacheNames = "shiro", allEntries = true)
    @Transactional
    public void saveRes(SysRoleResParam sysRoleResParam) {
        {
            SysRoleRes sysRoleRes = new SysRoleRes();
            sysRoleRes.setRoleId(sysRoleResParam.getId());
            sysRoleResMapper.delete(sysRoleRes);
        }
        if (sysRoleResParam.getIds() != null) {
            for (String id : sysRoleResParam.getIds()) {
                SysRoleRes sysRoleRes = new SysRoleRes();
                sysRoleRes.setRoleId(sysRoleResParam.getId());
                sysRoleRes.setResId(id);
                sysRoleResMapper.insertSelective(sysRoleRes);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<TreeNode> findTree(String deleteFlag) {
        return sysRoleMapper.findTree(deleteFlag);
    }

    private void children(String id, List<String> ids) {
        ids.add(id);
        SysRole record = new SysRole();
        record.setPid(id);
        List<SysRole> list = sysRoleMapper.select(record);
        for (SysRole sysRole : list) {
            children(sysRole.getId(), ids);
        }
    }
}
