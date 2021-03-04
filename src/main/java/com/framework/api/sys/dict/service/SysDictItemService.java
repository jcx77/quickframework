package com.framework.api.sys.dict.service;

import com.framework.commons.constant.DataConstant;
import com.framework.api.sys.dict.model.SysDictItem;
import com.framework.api.sys.dict.mapper.SysDictItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysDictItemService {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;



    /**
    * @Description: TODO( )
    * @Param: [ ]
    * @return: java.util.List<SysDictItem>
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @Transactional(readOnly = true)
    public List<SysDictItem> find(String dictId, String deleteFlag) {
        Example example = new Example(SysDictItem.class);
        example.orderBy("sort");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dictId", dictId);
        if (deleteFlag != null) {
            criteria.andEqualTo("deleteFlag", deleteFlag);
        }
        List<SysDictItem> list  =sysDictItemMapper.selectByExample(example);
        return list;
    }

    /**
    * @Description: TODO( 添加 )
    * @Param: [ sysDictItem ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @Transactional
    public void insert(SysDictItem sysDictItem) {
        //Example example = new Example(SysDictItem.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        //if (sysDictItemMapper.selectOneByExample(example) != null) {
        //    throw new BizException("不允许重复");
        //}
        sysDictItemMapper.insertSelective(sysDictItem);
    }

    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: com.zxc.toolsproject.api.sys.model.SysDictItem
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @Transactional(readOnly = true)
    public SysDictItem get(String id) {
        return sysDictItemMapper.selectByPrimaryKey(id);
    }

    /**
    * @Description: TODO( 更新实体 )
    * @Param: [ sysDictItem ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @Transactional
    public void update(SysDictItem sysDictItem) {
        //Example example = new Example(SysDictItem.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0)
        //.andNotEqualTo("id", sysDictItem.getId());
        //if (sysDictItemMapper.selectCountByExample(example) > 0) {
        //    throw new BizException("不允许重复");
        //}
        sysDictItemMapper.updateByPrimaryKeySelective(sysDictItem);
    }

    /**
    * @Description: TODO( 删除，将实体状态修改为1。 )
    * @Param: [ id ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-09-14 16:00:15
    */
    @Transactional
    public void delete(String id) {
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem.setId(id);
        sysDictItem.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        sysDictItemMapper.updateByPrimaryKeySelective(sysDictItem);
    }


}
