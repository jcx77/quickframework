package ${data.packageName};

import ${data.javaModel.packageName}.${data.javaModel.className};
import ${data.serviceName}.${data.serviceClassName};
import Response;
import ResponseResult;
import PageList;
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
    @RequestMapping(value = "/find")
    public ResponseResult<PageList<${data.javaModel.className}>> find(@RequestBody @Validated Param Param) {
        return new ResponseResult<>(new PageList<>(${data.lowerServiceClassName}.find(Param)));
    }


    /**
    * @Description: TODO()
    * @Param: [${data.javaModel.lowerClassName}]
    * @return:
    * @Author: xxx
    * @Date: ${data.date}
    */
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
    @RequestMapping(value = "/get/{id}")
    public ResponseResult<${data.javaModel.className}> get(@PathVariable String id) {
        return new ResponseResult<>(${data.lowerServiceClassName}.get(id));
    }

    /**
    * @Description: TODO( 更新 )
    * @Param: [  ]
    * @return:
    * @Author: xxx
    * @Date: ${data.date}
    */
    @RequestMapping(value = "/update")
    //@RequiresPermissions("zddyb:update")
    public Response update(@RequestBody @Validated ${data.javaModel.className} ${data.javaModel.lowerClassName}) {
        ${data.lowerServiceClassName}.update(${data.javaModel.lowerClassName});
        return Response.success();
    }


    /**
    * @Description: TODO(  )
    * @Param: [ id ]
    * @return:
    * @Author: xxx
    * @Date: ${data.date}
    */
    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable String id) {
        ${data.lowerServiceClassName}.delete(id);
        return Response.success();
    }

}
