package com.framework.api.sys.dict.service;


import com.framework.api.sys.dict.mapper.SysDictMapper;
import com.framework.api.sys.dict.model.SysDict;
import com.framework.commons.constant.DataConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;



    /**
    * @Description: TODO( )
    * @Param: [ ]
    * @return: java.util.List<SysDict>
    * @Author: xxx
    * @Date: 2020-09-13 20:51:52
    */
    @Transactional(readOnly = true)
    public List<SysDict> find() {
        Example example = new Example(SysDict.class);
        //PageHelper.startPage(Param.getPage(), Param.getRows());
        example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        example.orderBy("sort");
        return sysDictMapper.selectByExample(example);
    }

    /**
    * @Description: TODO( 添加 )
    * @Param: [ sysDict ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-13 20:51:52
    */
    @Transactional
    public void insert(SysDict sysDict) {
        //Example example = new Example(SysDict.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        //if (sysDictMapper.selectOneByExample(example) != null) {
        //    throw new BizException("不允许重复");
        //}
        sysDictMapper.insertSelective(sysDict);
    }

    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: com.zxc.toolsproject.api.sys.model.SysDict
    * @Author: xxx
    * @Date: 2020-09-13 20:51:52
    */
    @Transactional(readOnly = true)
    public SysDict get(String id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
    * @Description: TODO( 更新实体 )
    * @Param: [ sysDict ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-13 20:51:52
    */
    @Transactional
    public void update(SysDict sysDict) {
        //Example example = new Example(SysDict.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0)
        //.andNotEqualTo("id", sysDict.getId());
        //if (sysDictMapper.selectCountByExample(example) > 0) {
        //    throw new BizException("不允许重复");
        //}
        sysDictMapper.updateByPrimaryKeySelective(sysDict);
    }

    /**
    * @Description: TODO( 删除，将实体状态修改为1。 )
    * @Param: [ id ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-13 20:51:52
    */
    @Transactional
    public void delete(String id) {
        SysDict sysDict = new SysDict();
        sysDict.setId(id);
        sysDict.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        sysDictMapper.updateByPrimaryKeySelective(sysDict);
    }


}
