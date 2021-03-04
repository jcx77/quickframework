package com.framework.api.sys.res.controller;

import java.util.List;

import com.framework.api.sys.res.model.SysRes;
import com.framework.api.sys.res.service.SysResService;
import com.framework.commons.log.AutoLog;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import com.framework.commons.vo.ui.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sys/res")
public class SysResController {
	@Autowired
	private SysResService sysResService;

	@AutoLog(value = "查询资源")
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@RequiresPermissions("res:find")
	public ResponseResult<List<SysRes>> find() {
		return new ResponseResult<List<SysRes>>(sysResService.find());
	}

	@AutoLog(value = "获取资源")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseResult<SysRes> get(@PathVariable String id) {
		return new ResponseResult<SysRes>(sysResService.get(id));
	}

	@AutoLog(value = "添加资源")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	//@RequiresPermissions("res:insert")
	public Response insert(@RequestBody @Validated SysRes sysRes) {
		sysResService.insert(sysRes);
		return Response.success();
	}

	@AutoLog(value = "修改资源")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@RequiresPermissions("res:update")
	public Response update(@RequestBody @Validated SysRes sysRes) {
		sysResService.update(sysRes);
		return Response.success();
	}

	@AutoLog(value = "删除资源")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@RequiresPermissions("res:delete")
	public Response delete(@PathVariable String id) {
		sysResService.delete(id);
		return Response.success();
	}

	@AutoLog(value = "查询资源树")
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ResponseResult<List<TreeNode>> tree() {
		return new ResponseResult<List<TreeNode>>(sysResService.findTree());
	}
}