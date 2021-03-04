package com.framework.api.sys.user.mapper;

import com.framework.api.sys.user.model.SysUser;
import com.framework.commons.vo.ui.Option;
import com.framework.commons.vo.ui.TreeNode;
import com.framework.api.sys.vo.param.SysUserParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    List<SysUser> find(SysUserParam sysUserParam);

    SysUser get(String id);

    List<TreeNode> findResTreeById(String id);

    List<Option> findOption(String deleteFlag);
}