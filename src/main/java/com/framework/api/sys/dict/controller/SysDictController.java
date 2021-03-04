package com.framework.api.sys.dict.controller;


import com.framework.api.sys.dict.model.SysDict;
import com.framework.api.sys.dict.service.SysDictService;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: SysDict
    * @Author: xxx
    * @Date: 2020-09-13 21:02:16
    */
    @RequestMapping(value = "/find")
    public ResponseResult<List<SysDict>> find() {
        return new ResponseResult<>(sysDictService.find());
    }


    /**
    * @Description: TODO()
    * @Param: [sysDict]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-13 21:02:16
    */
    @RequestMapping(value = "/insert")
    //@RequiresPermissions("dict:insert")
    public Response insert(@RequestBody @Validated SysDict sysDict) {
        sysDictService.insert(sysDict);
        return Response.success();
    }


    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: SysDict
    * @Author: xxx
    * @Date: 2020-09-13 21:02:16
    */
    @RequestMapping(value = "/get/{id}")
    public ResponseResult<SysDict> get(@PathVariable String id) {
        return new ResponseResult<>(sysDictService.get(id));
    }

    /**
    * @Description: TODO( 更新 )
    * @Param: [  ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-13 21:02:16
    */
    @RequestMapping(value = "/update")
    @RequiresPermissions("dict:update")
    public Response update(@RequestBody @Validated SysDict sysDict) {
        sysDictService.update(sysDict);
        return Response.success();
    }


    /**
    * @Description: TODO(  )
    * @Param: [ id ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-13 21:02:16
    */
    @RequestMapping(value = "/delete/{id}")
    @RequiresPermissions("dict:delete")
    public Response delete(@PathVariable String id) {
        sysDictService.delete(id);
        return Response.success();
    }

}
