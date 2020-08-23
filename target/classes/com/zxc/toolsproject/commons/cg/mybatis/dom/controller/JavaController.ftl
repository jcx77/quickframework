package ${data.packageName};

import ${data.javaModel.packageName}.${data.javaModel.className};
import ${data.serviceName}.${data.serviceClassName};
import org.zxc.tools.commons.vo.response.Response;
import org.zxc.tools.commons.vo.response.ResponseResult;
import org.zxc.tools.commons.vo.ui.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/xxx/xxx")
public class ${data.className} {

    @Autowired
    private ${data.serviceClassName} ${data.lowerServiceClassName};

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: ${data.javaModel.className}
    * @Author: xxx
    * @Date: ${data.date}
    */
    //@AutoLog(value = "查询门类")
    @RequestMapping(value = "/find")
    public ResponseResult<PageList<${data.javaModel.className}>> find(@RequestBody @Validated Param Param) {
        return new ResponseResult<>(new PageList<>(${data.lowerServiceClassName}.find(Param)));
    }


    /**
    * @Description: TODO()
    * @Param: [${data.javaModel.lowerClassName}]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: ${data.date}
    */
    //@AutoLog(value = "添加门类")
    @RequestMapping(value = "/insert")
    //@RequiresPermissions("mlb:insert")
    public Response insert(@RequestBody @Validated ${data.javaModel.className} ${data.javaModel.lowerClassName}) {
        ${data.lowerServiceClassName}.insert(${data.javaModel.lowerClassName});
        return Response.success();
    }


    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: ${data.javaModel.className}
    * @Author: xxx
    * @Date: ${data.date}
    */
    //@AutoLog(value = "获取门类")
    @RequestMapping(value = "/get/{id}")
    public ResponseResult<${data.javaModel.className}> get(@PathVariable String id) {
        return new ResponseResult<>(${data.lowerServiceClassName}.get(id));
    }

    /**
    * @Description: TODO( 更新 )
    * @Param: [  ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: ${data.date}
    */
    //@AutoLog(value = "更新门类")
    @RequestMapping(value = "/update")
    //@RequiresPermissions("zddyb:update")
    public Response update(@RequestBody @Validated ${data.javaModel.className} ${data.javaModel.lowerClassName}) {
        ${data.lowerServiceClassName}.update(${data.javaModel.lowerClassName});
        return Response.success();
    }


    /**
    * @Description: TODO(  )
    * @Param: [ id ]
    * @return: org.sevensoft.jrdp.commons.vo.response.Response
    * @Author: xxx
    * @Date: ${data.date}
    */
    //@AutoLog(value = "删除门类")
    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable String id) {
        ${data.lowerServiceClassName}.delete(id);
        return Response.success();
    }

}
