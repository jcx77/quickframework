package com.framework.api.sys.organ.mapper;


import com.framework.api.sys.organ.model.SysOrgan;
import com.framework.commons.vo.ui.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysOrganMapper extends Mapper<SysOrgan> {
	List<TreeNode> findTree(String deleteFlag);
}