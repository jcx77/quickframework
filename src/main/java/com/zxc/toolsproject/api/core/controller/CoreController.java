package com.zxc.toolsproject.api.core.controller;

import com.zxc.toolsproject.api.core.model.SysUser;
import com.zxc.toolsproject.api.core.service.CoreService;
import com.zxc.toolsproject.api.core.vo.param.LoginParam;
import com.zxc.toolsproject.api.core.vo.result.LoginResult;
import com.zxc.toolsproject.api.core.vo.result.LoginUserResult;
import com.zxc.toolsproject.api.core.vo.ui.Param;
import com.zxc.toolsproject.commons.exception.BizException;
import com.zxc.toolsproject.commons.exception.WebException;
import com.zxc.toolsproject.commons.log.AutoLog;
import com.zxc.toolsproject.commons.log.LogType;
import com.zxc.toolsproject.commons.mybatis.tools.AuditTools;
import com.zxc.toolsproject.commons.shiro.subject.ShiroUser;
import com.zxc.toolsproject.commons.shiro.tools.ShiroTools;
import com.zxc.toolsproject.commons.utils.JwtUtils;
import com.zxc.toolsproject.commons.utils.RsaUtils;
import com.zxc.toolsproject.commons.vo.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
            throw new WebException ("用户不存在！");
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
        return new ResponseResult<>("登录成功", loginResult);
    }


    @AutoLog(value = "刷新令牌")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseResult<LoginResult> token() {
        ShiroUser shiroUser = ShiroTools.getShiroUser();
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(JwtUtils.sign(shiroUser.getId(), shiroUser.getPassword(), VALIDITY_TIME));
        return new ResponseResult<LoginResult>(loginResult);
    }


    @AutoLog(value = "获取登录用户")
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public ResponseResult<LoginUserResult> loginUser() {
        return new ResponseResult<LoginUserResult>(new LoginUserResult(ShiroTools.getShiroUser()));
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




}
