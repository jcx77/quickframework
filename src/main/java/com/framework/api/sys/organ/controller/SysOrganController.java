package com.framework.api.sys.organ.controller;

import com.framework.api.sys.organ.model.SysOrgan;
import com.framework.api.sys.organ.service.SysOrganService;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.log.AutoLog;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import com.framework.commons.vo.ui.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sys/organ")
public class SysOrganController {
	@Autowired
	private SysOrganService sysOrganService;

	@AutoLog(value = "查询机构")
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@RequiresPermissions("organ:find")
	public ResponseResult<List<SysOrgan>> find() {
		return new ResponseResult<List<SysOrgan>>(sysOrganService.find(DataConstant.DELETE_FLAG_0));
	}

	@AutoLog(value = "获取机构")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseResult<SysOrgan> get(@PathVariable String id) {
		return new ResponseResult<SysOrgan>(sysOrganService.get(id));
	}

	@AutoLog(value = "添加机构")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@RequiresPermissions("organ:insert")
	public Response insert(@RequestBody @Validated SysOrgan sysOrgan) {
		sysOrganService.insert(sysOrgan);
		return Response.success();
	}

	@AutoLog(value = "修改机构")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@RequiresPermissions("organ:update")
	public Response update(@RequestBody @Validated SysOrgan sysOrgan) {
		sysOrganService.update(sysOrgan);
		return Response.success();
	}

	@AutoLog(value = "删除机构")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@RequiresPermissions("organ:delete")
	public Response delete(@PathVariable String id) {
		sysOrganService.delete(id);
		return Response.success();
	}

	@AutoLog(value = "查询还原机构")
	@RequestMapping(value = "/find/recovery", method = RequestMethod.POST)
	@RequiresPermissions("organ:recovery")
	public ResponseResult<List<SysOrgan>> findRecovery() {
		return new ResponseResult<List<SysOrgan>>(sysOrganService.find(DataConstant.DELETE_FLAG_1));
	}

	@AutoLog(value = "还原机构")
	@RequestMapping(value = "/recovery/{id}", method = RequestMethod.GET)
	@RequiresPermissions("organ:recovery")
	public Response recovery(@PathVariable String id) {
		sysOrganService.recovery(id);
		return Response.success();
	}

	@AutoLog(value = "查询所有机构树")
	@RequestMapping(value = "/tree/all", method = RequestMethod.GET)
	public ResponseResult<List<TreeNode>> treeAll() {
		return new ResponseResult<List<TreeNode>>(sysOrganService.findTree(null));
	}

	@AutoLog(value = "查询机构树")
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ResponseResult<List<TreeNode>> tree() {
		return new ResponseResult<List<TreeNode>>(sysOrganService.findTree(DataConstant.DELETE_FLAG_0));
	}
}