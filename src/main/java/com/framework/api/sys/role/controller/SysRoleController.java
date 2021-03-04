package com.framework.api.sys.role.controller;

import com.framework.api.sys.role.model.SysRole;
import com.framework.api.sys.role.service.SysRoleService;
import com.framework.api.sys.vo.param.SysRoleResParam;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import com.framework.commons.vo.ui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //@AutoLog(value = "查询角色")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    //@RequiresPermissions("role:find")
    public ResponseResult<List<SysRole>> find() {
        return new ResponseResult<List<SysRole>>(sysRoleService.find(DataConstant.DELETE_FLAG_0));
    }

    //@AutoLog(value = "获取角色")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseResult<SysRole> get(@PathVariable String id) {
        return new ResponseResult<SysRole>(sysRoleService.get(id));
    }

    //@AutoLog(value = "添加角色")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    //@RequiresPermissions("role:insert")
    public Response insert(@RequestBody @Validated SysRole sysRole) {
        sysRoleService.insert(sysRole);
        return Response.success();
    }

    //@AutoLog(value = "修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    //@RequiresPermissions("role:update")
    public Response update(@RequestBody @Validated SysRole sysRole) {
        sysRoleService.update(sysRole);
        return Response.success();
    }

    //@AutoLog(value = "删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    //@RequiresPermissions("role:delete")
    public Response delete(@PathVariable String id) {
        sysRoleService.delete(id);
        return Response.success();
    }

    //@AutoLog(value = "查询还原角色")
    @RequestMapping(value = "/find/recovery", method = RequestMethod.POST)
    //@RequiresPermissions("role:recovery")
    public ResponseResult<List<SysRole>> findRecovery() {
        return new ResponseResult<List<SysRole>>(sysRoleService.find(DataConstant.DELETE_FLAG_1));
    }

    //@AutoLog(value = "还原角色")
    @RequestMapping(value = "/recovery/{id}", method = RequestMethod.GET)
    //@RequiresPermissions("role:recovery")
    public Response recovery(@PathVariable String id) {
        sysRoleService.recovery(id);
        return Response.success();
    }

    //@AutoLog(value = "查询角色功能权限树")
    @RequestMapping(value = "/res/tree/{id}", method = RequestMethod.GET)
    public ResponseResult<List<TreeNode>> res(@PathVariable String id) {
        return new ResponseResult<List<TreeNode>>(sysRoleService.findResTreeById(id));
    }

    //@AutoLog(value = "保存角色功能权限")
    @RequestMapping(value = "/res", method = RequestMethod.POST)
    //@RequiresPermissions("role:res")
    public Response res(@RequestBody @Validated SysRoleResParam sysRoleResParam) {
        sysRoleService.saveRes(sysRoleResParam);
        return Response.success();
    }

    //@AutoLog(value = "查询所有角色树")
    @RequestMapping(value = "/tree/all", method = RequestMethod.GET)
    public ResponseResult<List<TreeNode>> treeAll() {
        return new ResponseResult<List<TreeNode>>(sysRoleService.findTree(null));
    }

    //@AutoLog(value = "查询角色树")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseResult<List<TreeNode>> tree() {
        return new ResponseResult<List<TreeNode>>(sysRoleService.findTree(DataConstant.DELETE_FLAG_0));
    }
}