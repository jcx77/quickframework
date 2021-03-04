package com.framework.api.sys.param.service;

import com.framework.api.sys.param.mapper.SysParamMapper;
import com.framework.commons.exception.BizException;
import com.framework.api.sys.param.model.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import tk.mybatis.mapper.entity.Example;


import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
public class SysParamService {
    @Autowired
    private SysParamMapper sysParamMapper;
    @Autowired
    private LocalValidatorFactoryBean validator;

    @Transactional(readOnly = true)
    public List<SysParam> find() {
        Example example = new Example(SysParam.class);
        example.orderBy("sort");
        return sysParamMapper.selectByExample(example);
    }

    @CacheEvict(cacheNames = "shiro", key = "'param'")
    @Transactional
    public void save(SysParam[] sysParams) {
        if (sysParams != null) {
            for (SysParam sysParam : sysParams) {
                Set<ConstraintViolation<SysParam>> set = validator.validate(sysParam);
                for (ConstraintViolation<SysParam> violation : set) {
                    throw new BizException(violation.getMessage());
                }
                sysParamMapper.updateByPrimaryKeySelective(sysParam);
            }
        }
    }

}
