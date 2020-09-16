package com.zxc.toolsproject.api.sys.log.controller;

import com.zxc.toolsproject.api.sys.log.model.SysLog;
import com.zxc.toolsproject.api.sys.log.service.SysLogService;
import com.zxc.toolsproject.api.sys.vo.param.SysLogParam;
import com.zxc.toolsproject.commons.log.AutoLog;
import com.zxc.toolsproject.commons.vo.response.ResponseResult;
import com.zxc.toolsproject.commons.vo.ui.PageList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	@AutoLog(value = "查询日志")
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@RequiresPermissions("log:find")
	public ResponseResult<PageList<SysLog>> find(@RequestBody @Validated SysLogParam sysLogParam) {
		return new ResponseResult<>(new PageList<>(sysLogService.find(sysLogParam)));
	}
}