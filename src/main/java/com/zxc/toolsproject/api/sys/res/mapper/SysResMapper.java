package com.zxc.toolsproject.api.sys.res.mapper;

import com.zxc.toolsproject.api.sys.res.model.SysRes;
import com.zxc.toolsproject.commons.vo.ui.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResMapper extends Mapper<SysRes> {
    List<TreeNode> findTree();
}