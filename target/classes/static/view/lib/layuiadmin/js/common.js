layui.define([ 'layer', 'form' ], function(exports) {
	var $ = layui.$, layer = layui.layer, form = layui.form;
	$.ajaxSetup({
		beforeSend : function(xhr) {
			$.gui.mask();
			if ($.session.getToken()) {
				xhr.setRequestHeader('token', $.session.getToken());
			}
		},
		error : function(xhr, status, error) {
			$.gui.msg('请求失败');
		},
		complete : function(xhr) {
			$.gui.unmask();
		}
	});
	$.extend({
		gui : {
			mask : function() {
				layer.load(2);
			},
			unmask : function() {
				layer.closeAll('loading');
			},
			msg : function(msg, fn) {
				layer.msg(msg, {
					skin : 'layui-layer-hui',
					time : 2000
				}, fn);
			},
			open : function(option) {
				var option = option || {};
				if (!option.width) {
					option.width = ($(window).width() - 40);
				}
				if (!option.height) {
					option.height = ($(window).height() - 40);
				}
				layer.open({
					type : 2,
					maxmin : true,
					title : option.title,
					content : option.url,
					area : [ option.width + 'px', option.height + 'px' ]
				})
			},
			close : function() {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			info : function(text, yes) {
				layer.alert(text, {
					title : '系统提示',
					skin : 'layui-layer-lan',
					closeBtn : 0,
					icon : 0
				}, function(index) {
					if (typeof yes == 'function') {
						yes();
					}
					layer.close(index);
				});
			},
			success : function(text, yes) {
				layer.alert(text, {
					title : '系统提示',
					skin : 'layui-layer-lan',
					closeBtn : 0,
					icon : 1
				}, function(index) {
					if (typeof yes == 'function') {
						yes();
					}
					layer.close(index);
				});
			},
			error : function(text, yes) {
				layer.alert(text, {
					title : '系统提示',
					skin : 'layui-layer-lan',
					closeBtn : 0,
					icon : 2
				}, function(index) {
					if (typeof yes == 'function') {
						yes();
					}
					layer.close(index);
				});
			},
			confirm : function(text, yes, cancel) {
				layer.confirm(text, {
					title : '系统提示',
					skin : 'layui-layer-lan',
					closeBtn : 0,
					icon : 3
				}, function(index) {
					if (typeof yes == 'function') {
						yes();
					}
					layer.close(index);
				}, function(index) {
					if (typeof cancel == 'function') {
						cancel();
					}
					layer.close(index);
				});
			},
			handle : function(data, fn1, fn2) {
				if (data.status == 0) {
					$.gui.success(data.msg, fn1);
				} else if (data.status == 100) {
					$.gui.error(data.msg, function() {
						$.gui.toLogin();
					});
				} else {
					$.gui.error(data.msg, fn2);
				}
			},
			to : function(url) {
				window.location.href = url;
			},
			toLogin : function() {
				$.session.clear();
				$.gui.to($.http.baseUrl);
			},
			reload : function() {
				window.location.reload();
			}
		},
		util : {
			toTree : function(data) {
				var treeData = [], temp = [];
				for ( var i in data) {
					temp[data[i]['id']] = data[i];
				}
				for ( var i in data) {
					if (temp[data[i]['pid']] && data[i]['id'] != data[i]['pid']) {
						if (!temp[data[i]['pid']]['children']) {
							temp[data[i]['pid']]['children'] = [];
						}
						temp[data[i]['pid']]['children'].push(data[i]);
					} else {
						treeData.push(data[i]);
					}
				}
				return treeData;
			},
			toObject : function(data, attr1, attr2) {
				var objectData = {};
				for ( var i in data) {
					objectData[data[i][attr1]] = data[i][attr2];
				}
				return objectData;
			},
			toIds : function(data) {
				var ids = [];
				for ( var i in data) {
					ids.push(data[i]['id']);
				}
				return ids;
			}
		},
		form : {
			load : function(e, o) {
				var form = $('#' + e);
				if (form) {
					layui.each(o, function(attr, value) {
						var input = form.find('[name="' + attr + '"]');
						if (input[0]) {
							if (input.attr('type') == 'hidden' || input.attr('class').indexOf('layui-input') != -1 || input.attr('class').indexOf('layui-textarea') != -1) {
								input.val(value);
							} else if (input.attr('class') == 'xm-select-default') {
								s = xmSelect.get('#' + attr, true);
								if ($.isArray(value)) {
									s.setValue(value);
								} else {
									s.setValue([ value ]);
								}
							}
						}
					});
				}
			},
			val : function(e) {
				var o = {};
				var form = $('#' + e);
				form.find('[name]').each(function(i, item) {
					if (item.name) {
						var input = form.find('[name="' + item.name + '"]');
						if (input[0]) {
							if (input.attr('type') == 'hidden' || input.attr('class').indexOf('layui-input') != -1 || input.attr('class').indexOf('layui-textarea') != -1) {
								if (input.val()) {
									o[item.name] = input.val();
								} else {
									o[item.name] = '';
								}
							} else if (input.attr('class') == 'xm-select-default') {
								s = xmSelect.get('#' + item.name, true);
								var v = s.getValue('value');
								if (v.length > 0) {
									if (s.options.radio) {
										o[item.name] = v[0];
									} else {
										o[item.name] = v;
									}
								} else {
									o[item.name] = '';
								}
							}
						}
					}
				});
				if ($.isEmptyObject(o)) {
					return null;
				}
				return o;
			},
			reset : function(e) {
				var form = $('#' + e);
				form[0].reset();
				form.find('[class="xm-select-default"]').each(function(i, item) {
					if (item.name) {
						var input = form.find('[name="' + item.name + '"]');
						if (input[0]) {
							if (input.attr('class') == 'xm-select-default') {
								s = xmSelect.get('#' + item.name, true);
								s.reset();
							}
						}
					}
				});
			}
		},
		session : {
			setToken : function(token) {
				sessionStorage.token = token;
			},
			getToken : function() {
				return sessionStorage.token;
			},
			setLoginUser : function(loginUser) {
				if (loginUser) {
					sessionStorage.loginUser = JSON.stringify(loginUser);
				}
			},
			getLoginUser : function() {
				if (sessionStorage.loginUser) {
					return JSON.parse(sessionStorage.loginUser);
				} else {
					return null;
				}
			},
			setParam : function(param) {
				if (param) {
					sessionStorage.param = JSON.stringify(param);
				}
			},
			getParam : function() {
				if (sessionStorage.param) {
					return JSON.parse(sessionStorage.param);
				} else {
					return null;
				}
			},
			clear : function() {
				sessionStorage.clear();
			}
		},
		rsa : {
			encrypt : function(data) {
				if (data) {
					var rsa = new JSEncrypt();
					rsa.setPublicKey(config.publicKey);
					return rsa.encrypt(data);
				} else {
					return '';
				}
			}
		},
		shiro : {
			isPerms : function(perms) {
				if ($.session.getLoginUser()) {
					if ($.session.getLoginUser().perms.indexOf(perms) > -1) {
						return true;
					}
				} else {
					return false;
				}
			},
			render : function() {
				$('[shiro-perms]').each(function() {
					if (!$.shiro.isPerms($(this).attr('shiro-perms'))) {
						$(this).hide();
					}
				});
			}
		},
		http : {
			baseUrl : config.protocol() + '//' + config.host() + config.ctx,
			param : function(name) {
				return layui.url().search[name];
			},
			post : function(settings) {
				if (settings.url) {
					settings.url = $.http.baseUrl + settings.url;
				}
				if (settings.data) {
					settings.data = JSON.stringify(settings.data);
				}
				settings.contentType = 'application/json';
				$.post(settings);
			},
			get : function(settings) {
				if (settings.url) {
					settings.url = $.http.baseUrl + settings.url;
				}
				$.get(settings);
			},
			getJSON : function(url) {
				var settings = {
					type : 'get',
					async : false
				};
				if (url) {
					settings.url = $.http.baseUrl + url;
				}
				var res = $.ajax(settings).responseJSON;
				if (res.status == 0) {
					return res.result;
				} else if (res.status == 100) {
					$.gui.error(res.msg, function() {
						$.gui.toLogin();
					});
				} else {
					$.gui.error(res.msg);
					return null;
				}
			},
			postJSON : function(url, data) {
				var settings = {
					type : 'post',
					contentType : 'application/json',
					async : false
				};
				if (url) {
					settings.url = $.http.baseUrl + url;
				}
				if (data) {
					settings.data = JSON.stringify(data);
				}
				var res = $.ajax(settings).responseJSON;
				if (res.status == 0) {
					return res.result;
				} else if (res.status == 100) {
					$.gui.error(res.msg, function() {
						$.gui.toLogin();
					});
				} else {
					$.gui.error(res.msg);
					return null;
				}
			}
		}
	});
	form.verify({
		equals : function(value, item) {
			if ($('#' + $(item).attr('ref')).val() != value) {
				return '两次密码并不一致';
			}
		},
		minLen : function(value, item) {
			if (value) {
				if (value.length < parseInt($(item).attr('minLen'))) {
					return '最少输入' + $(item).attr('minLen') + '个字符';
				}
			}
		},
		maxLen : function(value, item) {
			if (value) {
				if (value.length > parseInt($(item).attr('maxLen'))) {
					return '最多输入' + $(item).attr('maxLen') + '个字符';
				}
			}
		},
		integer : function(value, item) {
			if (value) {
				if (!/^-?[1-9]\d*$/.test(value)) {
					return '必须是整数';
				}
			}
		},
		min : function(value, item) {
			if (parseInt(value) < parseInt($(item).attr('min'))) {
				return '不能小于' + $(item).attr('min');
			}
		},
		max : function(value, item) {
			if (parseInt(value) > parseInt($(item).attr('max'))) {
				return '不能大于' + $(item).attr('max');
			}
		}
	});
	exports('common', {});
});