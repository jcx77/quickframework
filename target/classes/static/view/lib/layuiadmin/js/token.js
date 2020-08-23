layui.define(function(exports) {
	var $ = layui.$;
	function token() {
		$.get({
			url : $.http.baseUrl + '/api/core/token',
			beforeSend : function(xhr) {
				if ($.session.getToken()) {
					xhr.setRequestHeader('token', $.session.getToken());
				}
			},
			success : function(data) {
				if (data.status == 0) {
					$.session.setToken(data.result.token);
				} else if (data.status == 100) {
					$.gui.error(data.msg, function() {
						$.gui.toLogin();
					});
				} else {
					$.gui.error(data.msg);
				}
			},
			complete : function(xhr) {
			}
		});
	}
	token();
	setInterval(token, 1 * 60 * 60 * 1000);
	exports('token', {});
});