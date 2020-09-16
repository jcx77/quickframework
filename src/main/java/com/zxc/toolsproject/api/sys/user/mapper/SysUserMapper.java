package com.zxc.toolsproject.api.sys.user.mapper;

import com.zxc.toolsproject.api.sys.user.model.SysUser;
import com.zxc.toolsproject.api.sys.vo.param.SysUserParam;
import com.zxc.toolsproject.commons.vo.ui.Option;
import com.zxc.toolsproject.commons.vo.ui.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    List<SysUser> find(SysUserParam sysUserParam);

    SysUser get(String id);

    List<TreeNode> findResTreeById(String id);

    List<Option> findOption(String deleteFlag);
}