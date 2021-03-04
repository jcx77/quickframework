package com.framework.api.sys.role.mapper;

import com.framework.api.sys.role.model.SysRole;
import com.framework.commons.vo.ui.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    List<TreeNode> findResTreeById(String id);

    List<TreeNode> findTree(String deleteFlag);
}