layui.define(function(exports) {
	var $ = layui.$;
	if (!$.session.getToken()) {
		$.gui.toLogin();
	}
	exports('shiro', {});
});