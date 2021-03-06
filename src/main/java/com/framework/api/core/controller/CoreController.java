package com.framework.api.core.controller;

import com.framework.api.core.vo.param.LoginParam;
import com.framework.api.core.vo.param.PasswordParam;
import com.framework.api.core.vo.result.LoginResult;
import com.framework.api.core.vo.result.LoginUserResult;
import com.framework.api.core.vo.ui.Param;
import com.framework.api.sys.user.model.SysUser;
import com.framework.api.test.vo.User;
import com.framework.commons.exception.WebException;
import com.framework.commons.log.AutoLog;
import com.framework.commons.log.LogType;
import com.framework.commons.mybatis.tools.AuditTools;
import com.framework.commons.shiro.subject.ShiroUser;
import com.framework.commons.shiro.tools.ShiroTools;
import com.framework.commons.utils.JwtUtils;
import com.framework.commons.utils.RsaUtils;
import com.framework.commons.vo.response.ResponseResult;
import com.framework.commons.vo.ui.Option;
import com.framework.api.core.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/core")
public class CoreController {
    private static final long VALIDITY_TIME = 2 * 60 * 60 * 1000;
    @Autowired
    private CoreService coreService;

    /**
    * @Description: TODO(  )
    * @Param: [  ]
    * @return: SysUser
    * @Author: xxx
    * @Date: 2020-08-06 20:07:21
    */
    @AutoLog(value = "登录", type = LogType.LOGIN)
    @RequestMapping(value = "/login")
    public ResponseResult<LoginResult> find(@RequestBody @Validated LoginParam loginParam) {
        SysUser sysUser=coreService.getSysUserByAccount(loginParam.getAccount());
        if(sysUser == null){
            throw new WebException("用户不存在！");
        }
        String password = RsaUtils.decrypt(loginParam.getPassword());
        if (password == null) {
            throw new WebException("密码无效");
        }
        String a= ShiroTools.md5(password, sysUser.getId());

        if (!ShiroTools.md5(password, sysUser.getId()).equals(sysUser.getPassword())) {
            throw new WebException("密码错误");
        }
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(JwtUtils.sign(sysUser.getId(), sysUser.getPassword(), VALIDITY_TIME));
        AuditTools.setUserId(sysUser.getId());
        contextLoads();
        return new ResponseResult<>("登录成功", loginResult);
    }
    @Autowired
    public RedisTemplate redisTemplate;
    /**
    * @Description: TODO(redis测试代码)
    * @Param: []
    * @return: void
    * @Author: zcx
    * @Date: 2021/3/6 18:41
    */
    public void contextLoads() {
        redisTemplate.opsForValue().set("1",new User("sd",2));
        //redisTemplate.expire("1",3600, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("1"));
    }
    @AutoLog(value = "修改登录密码")
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ResponseResult<LoginResult> password(@RequestBody @Validated PasswordParam passwordParam) {
        String oldPassword = RsaUtils.decrypt(passwordParam.getOldPassword());
        if (oldPassword == null) {
            throw new WebException("当前密码无效");
        }
        String newPassword = RsaUtils.decrypt(passwordParam.getNewPassword());
        if (newPassword == null) {
            throw new WebException("新密码无效");
        }
        ShiroUser shiroUser = ShiroTools.getShiroUser();
        if (!shiroUser.getPassword().equals(ShiroTools.md5(oldPassword, shiroUser.getId()))) {
            throw new WebException("当前密码输入错误");
        }
        String password = coreService.updatePassword(shiroUser.getId(), newPassword);
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(JwtUtils.sign(shiroUser.getId(), password));
        return new ResponseResult<LoginResult>(loginResult);
    }

    @AutoLog(value = "刷新令牌")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseResult<LoginResult> token() {
        ShiroUser shiroUser = ShiroTools.getShiroUser();
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(JwtUtils.sign(shiroUser.getId(), shiroUser.getPassword(), VALIDITY_TIME));
        return new ResponseResult<>(loginResult);
    }


    @AutoLog(value = "获取登录用户")
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public ResponseResult<LoginUserResult> loginUser() {
        return new ResponseResult<>(new LoginUserResult(ShiroTools.getShiroUser()));
    }

    @AutoLog(value = "获取系统参数")
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public ResponseResult<Param> param() {
        Param param = coreService.getParam();
        if (param == null) {
            throw new WebException("获取系统参数错误");
        }
        return new ResponseResult<Param>(param);
    }


    @AutoLog(value = "查询数据字典")
    @RequestMapping(value = "/option/{dictId}", method = RequestMethod.GET)
    public ResponseResult<List<Option>> option(@PathVariable String dictId) {
        return new ResponseResult<List<Option>>(coreService.findOption(dictId));
    }

    @AutoLog(value = "查询所有数据字典")
    @RequestMapping(value = "/option/all/{dictId}", method = RequestMethod.GET)
    public ResponseResult<List<Option>> optionAll(@PathVariable String dictId) {
        return new ResponseResult<List<Option>>(coreService.findOptionAll(dictId));
    }

}
