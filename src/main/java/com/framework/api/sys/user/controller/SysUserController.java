package com.framework.api.sys.user.controller;

import com.framework.api.sys.user.model.SysUser;
import com.framework.api.sys.user.service.SysUserService;
import com.framework.commons.log.AutoLog;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import com.framework.commons.vo.ui.Option;
import com.framework.commons.vo.ui.PageList;
import com.framework.commons.vo.ui.TreeNode;
import com.framework.api.sys.vo.param.SysUserParam;
import com.framework.api.sys.vo.param.SysUserResParam;
import com.framework.commons.constant.DataConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sys/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@AutoLog(value = "查询用户")
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@RequiresPermissions("user:find")
	public ResponseResult<PageList<SysUser>> find(@RequestBody @Validated SysUserParam sysUserParam) {
		sysUserParam.setDeleteFlag(DataConstant.DELETE_FLAG_0);
		return new ResponseResult<PageList<SysUser>>(new PageList<SysUser>(sysUserService.find(sysUserParam)));
	}

	@AutoLog(value = "获取用户")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseResult<SysUser> get(@PathVariable String id) {
		SysUser sysUser = sysUserService.get(id);
		return new ResponseResult<SysUser>(sysUser);
	}

	@AutoLog(value = "添加用户")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@RequiresPermissions("user:insert")
	public Response insert(@RequestBody @Validated SysUser sysUser) throws Exception {
		String id=sysUserService.insert(sysUser);
		//return Response.success();
		return new ResponseResult<String>(id);
	}

	@AutoLog(value = "修改用户")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@RequiresPermissions("user:update")
	public Response update(@RequestBody @Validated SysUser sysUser) throws Exception {
		sysUserService.update(sysUser);
		return Response.success();
	}

	@AutoLog(value = "删除用户")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@RequiresPermissions("user:delete")
	public Response delete(@PathVariable String id) {
		sysUserService.delete(id);
		return Response.success();
	}

	@AutoLog(value = "查询还原用户")
	@RequestMapping(value = "/find/recovery", method = RequestMethod.POST)
	@RequiresPermissions("user:recovery")
	public ResponseResult<PageList<SysUser>> findRecovery(@Validated SysUserParam sysUserParam) {
		sysUserParam.setDeleteFlag(DataConstant.DELETE_FLAG_1);
		return new ResponseResult<PageList<SysUser>>(new PageList<SysUser>(sysUserService.find(sysUserParam)));
	}

	@AutoLog(value = "还原用户")
	@RequestMapping(value = "/recovery/{id}", method = RequestMethod.GET)
	@RequiresPermissions("user:recovery")
	public Response recovery(@PathVariable String id) {
		sysUserService.recovery(id);
		return Response.success();
	}


	@AutoLog(value = "重置密码")
	@RequestMapping(value = "/password/{id}", method = RequestMethod.GET)
	@RequiresPermissions("user:password")
	public Response password(@PathVariable String id) {
		sysUserService.resetPassword(id);
		return Response.success();
	}

	@AutoLog(value = "查询用户功能权限树")
	@RequestMapping(value = "/res/tree/{id}", method = RequestMethod.GET)
	public ResponseResult<List<TreeNode>> res(@PathVariable String id) {
		return new ResponseResult<List<TreeNode>>(sysUserService.findResTreeById(id));
	}

	@AutoLog(value = "保存用户功能权限")
	@RequestMapping(value = "/res", method = RequestMethod.POST)
	@RequiresPermissions("user:res")
	public Response res(@RequestBody @Validated SysUserResParam sysUserResParam) {
		sysUserService.saveRes(sysUserResParam);
		return Response.success();
	}

	@AutoLog(value = "查询所有用户列表")
	@RequestMapping(value = "/option/all", method = RequestMethod.GET)
	public ResponseResult<List<Option>> optionAll() {
		return new ResponseResult<List<Option>>(sysUserService.findOption(null));
	}

	@AutoLog(value = "查询用户列表")
	@RequestMapping(value = "/option", method = RequestMethod.GET)
	public ResponseResult<List<Option>> option() {
		return new ResponseResult<List<Option>>(sysUserService.findOption(DataConstant.DELETE_FLAG_0));
	}
	


}