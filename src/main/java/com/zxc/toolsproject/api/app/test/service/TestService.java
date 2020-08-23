package com.zxc.toolsproject.api.app.test.service;

import com.zxc.toolsproject.api.app.test.mapper.TestMapper;
import com.zxc.toolsproject.api.app.test.model.Test;

import com.zxc.toolsproject.commons.constant.DataConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;



    /**
    * @Description: TODO( )
    * @Param: [ ]
    * @return: java.util.List<Test>
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    @Transactional(readOnly = true)
    public List<Test> find() {
        Example example = new Example(Test.class);
        //PageHelper.startPage(Param.getPage(), Param.getRows());
        example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        return testMapper.selectByExample(example);
    }

    /**
    * @Description: TODO( 添加 )
    * @Param: [ test ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    @Transactional
    public void insert(Test test) {
        //Example example = new Example(Test.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        //if (testMapper.selectOneByExample(example) != null) {
        //    throw new BizException("不允许重复");
        //}
        testMapper.insertSelective(test);
    }

    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: com.zxc.toolsproject.api.app.test.model.Test
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    @Transactional(readOnly = true)
    public Test get(String id) {
        return testMapper.selectByPrimaryKey(id);
    }

    /**
    * @Description: TODO( 更新实体 )
    * @Param: [ test ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    @Transactional
    public void update(Test test) {
        //Example example = new Example(Test.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0)
        //.andNotEqualTo("id", test.getId());
        //if (testMapper.selectCountByExample(example) > 0) {
        //    throw new BizException("不允许重复");
        //}
        testMapper.updateByPrimaryKeySelective(test);
    }

    /**
    * @Description: TODO( 删除，将实体状态修改为1。 )
    * @Param: [ id ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-03 10:22:11
    */
    @Transactional
    public void delete(String id) {
        Test test = new Test();
//        test.setId(id);
//        test.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        testMapper.updateByPrimaryKeySelective(test);
    }


}
