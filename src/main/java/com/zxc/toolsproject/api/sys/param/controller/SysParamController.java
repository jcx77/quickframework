package com.zxc.toolsproject.api.sys.param.controller;

import com.zxc.toolsproject.api.sys.param.model.SysParam;
import com.zxc.toolsproject.api.sys.param.service.SysParamService;
import com.zxc.toolsproject.commons.vo.response.Response;
import com.zxc.toolsproject.commons.vo.response.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/sys/param")
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: SysParam
    * @Author: xxx
    * @Date: 2020-09-15 10:59:26
    */
    @RequestMapping(value = "/find")
    public ResponseResult<List<SysParam>> find() {
        return new ResponseResult<>(sysParamService.find());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("param:save")
    public Response save(@RequestBody SysParam[] sysParams) {
        sysParamService.save(sysParams);
        return Response.success();
    }


}
