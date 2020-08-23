package com.zxc.toolsproject.api.app.test.controller;

import com.zxc.toolsproject.api.app.test.model.Test;
import com.zxc.toolsproject.api.app.test.service.TestService;

import com.zxc.toolsproject.commons.vo.response.Response;
import com.zxc.toolsproject.commons.vo.response.ResponseResult;
import com.zxc.toolsproject.commons.vo.ui.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/xxx/xxx")
public class TestController {

    @Autowired
    private TestService testService;

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: Test
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    //@AutoLog(value = "查询门类")
    @RequestMapping(value = "/find")
    public ResponseResult<PageList<Test>> find() {
        return new ResponseResult<>(new PageList<>(testService.find()));
    }


    /**
    * @Description: TODO()
    * @Param: [test]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    //@AutoLog(value = "添加门类")
    @RequestMapping(value = "/insert")
    //@RequiresPermissions("mlb:insert")
    public Response insert(@RequestBody @Validated Test test) {
        testService.insert(test);
        return Response.success();
    }


    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: Test
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    //@AutoLog(value = "获取门类")
    @RequestMapping(value = "/get/{id}")
    public ResponseResult<Test> get(@PathVariable String id) {
        return new ResponseResult<>(testService.get(id));
    }

    /**
    * @Description: TODO( 更新 )
    * @Param: [  ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    //@AutoLog(value = "更新门类")
    @RequestMapping(value = "/update")
    //@RequiresPermissions("zddyb:update")
    public Response update(@RequestBody @Validated Test test) {
        testService.update(test);
        return Response.success();
    }


    /**
    * @Description: TODO(  )
    * @Param: [ id ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    //@AutoLog(value = "删除门类")
    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable String id) {
        testService.delete(id);
        return Response.success();
    }

}
