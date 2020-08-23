package com.zxc.toolsproject.api.core.service;

import com.zxc.toolsproject.api.core.model.SysUser;
import com.zxc.toolsproject.api.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

@Service
public class SysUserService  {

    @Autowired
    private SysUserMapper sysUserMapper;



    /**
    * @Description: TODO( )
    * @Param: [ ]
    * @return: java.util.List<SysUser>
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @Transactional(readOnly = true)
    public List<SysUser> find() {
        Example example = new Example(SysUser.class);
        //PageHelper.startPage(Param.getPage(), Param.getRows());
       // example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        return sysUserMapper.selectByExample(example);
    }

    /**
    * @Description: TODO( 添加 )
    * @Param: [ sysUser ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @Transactional
    public void insert(SysUser sysUser) {
        //Example example = new Example(SysUser.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0);
        //if (sysUserMapper.selectOneByExample(example) != null) {
        //    throw new BizException("不允许重复");
        //}
        sysUserMapper.insertSelective(sysUser);
    }

    /**
    * @Description: TODO( 获取 )
    * @Param: [ id ]
    * @return: com.zxc.toolsproject.api.core.model.SysUser
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @Transactional(readOnly = true)
    public SysUser get(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
    * @Description: TODO( 更新实体 )
    * @Param: [ sysUser ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @Transactional
    public void update(SysUser sysUser) {
        //Example example = new Example(SysUser.class);
        //example.createCriteria().andEqualTo("deleteFlag", DataConstant.DELETE_FLAG_0)
        //.andNotEqualTo("id", sysUser.getId());
        //if (sysUserMapper.selectCountByExample(example) > 0) {
        //    throw new BizException("不允许重复");
        //}
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
    * @Description: TODO( 删除，将实体状态修改为1。 )
    * @Param: [ id ]
    * @return: void
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @Transactional
    public void delete(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        //sysUser.setDeleteFlag(DataConstant.DELETE_FLAG_1);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }



}
