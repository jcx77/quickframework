package com.framework.api.sys.user.service;

import com.framework.api.sys.user.mapper.SysUserRoleMapper;
import com.framework.api.sys.user.model.SysUser;
import com.framework.api.sys.user.model.SysUserOrgan;
import com.framework.commons.shiro.tools.ShiroTools;
import com.framework.commons.vo.ui.Option;
import com.framework.commons.vo.ui.TreeNode;
import com.github.pagehelper.PageHelper;
import com.framework.api.sys.user.mapper.SysUserMapper;
import com.framework.api.sys.user.mapper.SysUserOrganMapper;
import com.framework.api.sys.user.mapper.SysUserResMapper;
import com.framework.api.sys.user.model.SysUserRes;
import com.framework.api.sys.user.model.SysUserRole;
import com.framework.api.sys.vo.param.SysUserParam;
import com.framework.api.sys.vo.param.SysUserResParam;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.exception.BizException;
import com.framework.commons.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserOrganMapper sysUserOrganMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserResMapper sysUserResMapper;


    @Transactional(readOnly = true)
    public List<SysUser> find(SysUserParam sysUserParam) {
        PageHelper.startPage(sysUserParam.getPage(), sysUserParam.getRows());
        return sysUserMapper.find(sysUserParam);
    }

    @Transactional(readOnly = true)
    public SysUser get(String id) {
        return sysUserMapper.get(id);
    }

    @Transactional
    public String insert(SysUser sysUser) throws Exception {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", sysUser.getAccount());
        if (sysUserMapper.selectCountByExample(example) > 0) {
            throw new BizException("帐号不允许重复");
        }
        sysUser.setId(StringUtils.uuid());
        sysUser.setPassword(ShiroTools.md5(sysUser.getAccount(), sysUser.getId()));
        sysUser.setOrganId(sysUser.getOrganIds()[0]);
        sysUserMapper.insertSelective(sysUser);
        save(sysUser.getId(), sysUser.getOrganIds(), sysUser.getRoleIds());

        return sysUser.getId();
    }

    @CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#sysUser.id")
    @Transactional
    public void update(SysUser sysUser) throws Exception {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", sysUser.getAccount());
        criteria.andNotEqualTo("id", sysUser.getId());
        if (sysUserMapper.selectCountByExample(example) > 0) {
            throw new BizException("帐号不允许重复");
        }
        sysUser.setOrganId(sysUser.getOrganIds()[0]);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        save(sysUser.getId(), sysUser.getOrganIds(), sysUser.getRoleIds());

    }

    @CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#id")
    @Transactional
    public void delete(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Transactional
    public void recovery(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setDeleteFlag(DataConstant.DELETE_FLAG_0);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#id")
    @Transactional
    public void resetPassword(String id) {
        SysUser record = sysUserMapper.selectByPrimaryKey(id);
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(ShiroTools.md5(record.getAccount(), record.getId()));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @CacheEvict(cacheNames = "shiro", key = "'shiro_user_'+#sysUserResParam.id")
    @Transactional
    public void saveRes(SysUserResParam sysUserResParam) {
        {
            SysUserRes sysUserRes = new SysUserRes();
            sysUserRes.setUserId(sysUserResParam.getId());
            sysUserResMapper.delete(sysUserRes);
        }
        if (sysUserResParam.getIds() != null) {
            for (String id : sysUserResParam.getIds()) {
                SysUserRes sysUserRes = new SysUserRes();
                sysUserRes.setUserId(sysUserResParam.getId());
                sysUserRes.setResId(id);
                sysUserResMapper.insertSelective(sysUserRes);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<TreeNode> findResTreeById(String id) {
        return sysUserMapper.findResTreeById(id);
    }

    @Transactional(readOnly = true)
    public List<Option> findOption(String deleteFlag) {
        return sysUserMapper.findOption(deleteFlag);
    }

    private void save(String id, String[] organIds, String[] roleIds) {
        {
            SysUserOrgan sysUserOrgan = new SysUserOrgan();
            sysUserOrgan.setUserId(id);
            sysUserOrganMapper.delete(sysUserOrgan);
        }
        if (organIds != null) {
            for (String organId : organIds) {
                SysUserOrgan sysUserOrgan = new SysUserOrgan();
                sysUserOrgan.setUserId(id);
                sysUserOrgan.setOrganId(organId);
                sysUserOrganMapper.insertSelective(sysUserOrgan);
            }
        }
        {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(id);
            sysUserRoleMapper.delete(sysUserRole);
        }
        if (roleIds != null) {
            for (String roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(id);
                sysUserRole.setRoleId(roleId);
                sysUserRoleMapper.insertSelective(sysUserRole);
            }
        }
    }

}