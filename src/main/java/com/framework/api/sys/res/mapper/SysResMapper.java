package com.framework.api.sys.res.mapper;

import com.framework.commons.vo.ui.TreeNode;
import com.framework.api.sys.res.model.SysRes;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResMapper extends Mapper<SysRes> {
    List<TreeNode> findTree();
}