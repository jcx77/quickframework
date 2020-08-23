INSERT INTO SYS_PARAM(CODE_,NAME_,VALUE_,SORT_)
VALUES('project','项目名称','Java快速开发平台',1);
INSERT INTO SYS_PARAM(CODE_,NAME_,VALUE_,SORT_)
VALUES('version','项目版本','1.0.0',2);
INSERT INTO SYS_PARAM(CODE_,NAME_,VALUE_,SORT_)
VALUES('copyright','版权所有','2019-2020 zz',3);

INSERT INTO SYS_DICT(ID_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('flag','是否标记',1,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT(ID_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('res_type','资源类型',2,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT(ID_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('log_type','日志类型',3,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT(ID_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('job_status','任务状态',4,'admin',current_timestamp,'0');

INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'flag','1','是',1,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'flag','0','否',2,'admin',current_timestamp,'0');

INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'res_type','1','菜单',1,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'res_type','2','按钮',2,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'res_type','3','接口',3,'admin',current_timestamp,'0');

INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'log_type','0','操作日志',1,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'log_type','1','登录日志',2,'admin',current_timestamp,'0');

INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'job_status','0','已启动',1,'admin',current_timestamp,'0');
INSERT INTO SYS_DICT_ITEM(ID_,DICT_ID,CODE_,NAME_,SORT_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'job_status','1','已停止',2,'admin',current_timestamp,'0');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('sys','系统管理','1','layui-icon-set',null,10,'1','-1');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('param:find','系统参数','1',null,'sys/param/param_find.html',10,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('param:save','保存','2',null,null,1,'1','param:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:find','数据字典','1',null,'sys/dict/dict_find.html',20,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:insert','添加','2',null,null,1,'1','dict:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:update','修改','2',null,null,2,'1','dict:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:delete','删除','2',null,null,3,'1','dict:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:recovery','还原','2',null,null,4,'1','dict:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:item:find','字典项目','2',null,null,5,'1','dict:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:item:insert','添加','2',null,null,1,'1','dict:item:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:item:update','修改','2',null,null,2,'1','dict:item:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:item:delete','删除','2',null,null,3,'1','dict:item:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('dict:item:recovery','还原','2',null,null,4,'1','dict:item:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('res:find','资源管理','1',null,'sys/res/res_find.html',30,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('res:insert','添加','2',null,null,1,'1','res:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('res:update','修改','2',null,null,2,'1','res:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('res:delete','删除','2',null,null,3,'1','res:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:find','角色管理','1',null,'sys/role/role_find.html',40,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:insert','添加','2',null,null,1,'1','role:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:update','修改','2',null,null,2,'1','role:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:delete','删除','2',null,null,3,'1','role:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:recovery','还原','2',null,null,4,'1','role:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('role:res','功能权限','2',null,null,5,'1','role:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('organ:find','组织机构','1',null,'sys/organ/organ_find.html',50,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('organ:insert','添加','2',null,null,1,'1','organ:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('organ:update','修改','2',null,null,2,'1','organ:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('organ:delete','删除','2',null,null,3,'1','organ:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('organ:recovery','还原','2',null,null,4,'1','organ:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:find','用户管理','1',null,'sys/user/user_find.html',60,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:insert','添加','2',null,null,1,'1','user:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:update','修改','2',null,null,2,'1','user:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:delete','删除','2',null,null,3,'1','user:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:recovery','还原','2',null,null,4,'1','user:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:password','重置密码','2',null,null,5,'1','user:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('user:res','功能权限','2',null,null,6,'1','user:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:find','定时任务','1',null,'sys/job/job_find.html',70,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:insert','添加','2',null,null,1,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:update','修改','2',null,null,2,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:delete','删除','2',null,null,3,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:recovery','还原','2',null,null,4,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:start','启动','2',null,null,6,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:stop','停止','2',null,null,7,'1','job:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('job:log:find','任务日志','2',null,null,5,'1','job:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('log:find','系统日志','1',null,'sys/log/log_find.html',80,'1','sys');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('seq:find','序列管理','1',null,'sys/seq/seq_find.html',90,'1','sys');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('seq:insert','添加','2',null,null,1,'1','seq:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('seq:update','修改','2',null,null,2,'1','seq:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('seq:delete','删除','2',null,null,3,'1','seq:find');
INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('seq:dtl:find','序列明细','2',null,null,4,'1','seq:find');

INSERT INTO SYS_RES(ID_,NAME_,TYPE_,ICON_,URL_,SORT_,SHOW_,PID_)
VALUES('report:designer','报表设计器','1',null,'sys/report/designer.html',200,'1','sys');

INSERT INTO SYS_ORGAN(ID_,CODE_,NAME_,PID_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('organ','00','总机构','-1','admin',current_timestamp,'0');

INSERT INTO SYS_USER(ID_,ACCOUNT_,PASSWORD_,NAME_,LOCKED_,ORGAN_ID,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES('admin','admin','df655ad8d3229f3269fad2a8bab59b6c','管理员','0','organ','admin',current_timestamp,'0');

INSERT INTO SYS_USER_ORGAN(id_,user_id,organ_id)
VALUES(replace(uuid(),'-',''),'admin','organ');

INSERT INTO SYS_JOB(ID_,NAME_,CRON_,PARAM_,DESC_,STATUS_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'org.sevensoft.jrdp.api.job.LogJob','0 0 0 1/1 * ? *','30','清理系统日志，参数为保留天数','0','admin',current_timestamp,'0');
INSERT INTO SYS_JOB(ID_,NAME_,CRON_,PARAM_,DESC_,STATUS_,CREATED_BY,CREATED_TIME,DELETE_FLAG)
VALUES(replace(uuid(),'-',''),'org.sevensoft.jrdp.api.job.JobLogJob','0 0 0 1/1 * ? *','30','清理任务日志，参数为保留天数','0','admin',current_timestamp,'0');