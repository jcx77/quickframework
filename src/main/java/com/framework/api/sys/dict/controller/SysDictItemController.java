package com.framework.api.sys.dict.controller;

import com.framework.api.sys.dict.model.SysDictItem;
import com.framework.api.sys.dict.service.SysDictItemService;
import com.framework.commons.constant.DataConstant;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/sys/dict/item")
public class SysDictItemController {

    @Autowired
    private SysDictItemService sysDictItemService;

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: SysDictItem
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @RequestMapping(value = "/find/{dictId}", method = RequestMethod.POST)
    public ResponseResult<List<SysDictItem>> find(@PathVariable String dictId) {
        return new ResponseResult<>(sysDictItemService.find(dictId, DataConstant.DELETE_FLAG_0));
    }


    /**
    * @Description: TODO()
    * @Param: [sysDictItem]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @RequestMapping(value = "/insert")
    //@RequiresPermissions("mlb:insert")
    public Response insert(@RequestBody @Validated SysDictItem sysDictItem) {
        sysDictItemService.insert(sysDictItem);
        return Response.success();
    }


    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: SysDictItem
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @RequestMapping(value = "/get/{id}")
    public ResponseResult<SysDictItem> get(@PathVariable String id) {
        return new ResponseResult<>(sysDictItemService.get(id));
    }

    /**
    * @Description: TODO( 更新 )
    * @Param: [  ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @RequestMapping(value = "/update")
    //@RequiresPermissions("zddyb:update")
    public Response update(@RequestBody @Validated SysDictItem sysDictItem) {
        sysDictItemService.update(sysDictItem);
        return Response.success();
    }


    /**
    * @Description: TODO(  )
    * @Param: [ id ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable String id) {
        sysDictItemService.delete(id);
        return Response.success();
    }

}
