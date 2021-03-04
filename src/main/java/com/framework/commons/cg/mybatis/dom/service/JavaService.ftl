package ${data.packageName};

import ${data.javaModel.packageName}.${data.javaModel.className};
import ${data.mapperName}.${data.mapperClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import BizException;
import DataConstant;
import com.github.pagehelper.PageHelper;

import java.util.List;

@Service
public class ${data.className} {

    @Autowired
    private ${data.mapperClassName} ${data.lowerMapperClassName};



    /**
    * @Description: TODO( )
    * @Param: [ ]
    * @return: java.util.List<${data.javaModel.className}>
    * @Author: xxx
    * @Date: ${data.date}
    */
    @Transactional(readOnly = true)
    public List<${data.javaModel.className}> find() {
        Example example = new Example(${data.javaModel.className}.class);
        //PageHelper.startPage(Param.getPage(), Param.getRows());
        example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        return ${data.lowerMapperClassName}.selectByExample(example);
    }

    /**
    * @Description: TODO( 添加 )
    * @Param: [ ${data.javaModel.lowerClassName} ]
    * @return: void
    * @Author: xxx
    * @Date: ${data.date}
    */
    @Transactional
    public void insert(${data.javaModel.className} ${data.javaModel.lowerClassName}) {
        //Example example = new Example(${data.javaModel.className}.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        //if (${data.lowerMapperClassName}.selectOneByExample(example) != null) {
        //    throw new BizException("不允许重复");
        //}
        ${data.lowerMapperClassName}.insertSelective(${data.javaModel.lowerClassName});
    }

    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: ${data.javaModel.packageName}.${data.javaModel.className}
    * @Author: xxx
    * @Date: ${data.date}
    */
    @Transactional(readOnly = true)
    public ${data.javaModel.className} get(String id) {
        return ${data.lowerMapperClassName}.selectByPrimaryKey(id);
    }

    /**
    * @Description: TODO( 更新实体 )
    * @Param: [ ${data.javaModel.lowerClassName} ]
    * @return: void
    * @Author: xxx
    * @Date: ${data.date}
    */
    @Transactional
    public void update(${data.javaModel.className} ${data.javaModel.lowerClassName}) {
        //Example example = new Example(${data.javaModel.className}.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0)
        //.andNotEqualTo("id", ${data.javaModel.lowerClassName}.getId());
        //if (${data.lowerMapperClassName}.selectCountByExample(example) > 0) {
        //    throw new BizException("不允许重复");
        //}
        ${data.lowerMapperClassName}.updateByPrimaryKeySelective(${data.javaModel.lowerClassName});
    }

    /**
    * @Description: TODO( 删除，将实体状态修改为1。 )
    * @Param: [ id ]
    * @return: void
    * @Author: xxx
    * @Date: ${data.date}
    */
    @Transactional
    public void delete(String id) {
        ${data.javaModel.className} ${data.javaModel.lowerClassName} = new ${data.javaModel.className}();
        ${data.javaModel.lowerClassName}.setId(id);
        ${data.javaModel.lowerClassName}.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        ${data.lowerMapperClassName}.updateByPrimaryKeySelective(${data.javaModel.lowerClassName});
    }


}
