$.ajaxSetup({
	beforeSend : function(xhr) {
		if (sessionStorage.token) {
			xhr.setRequestHeader('token', sessionStorage.token);
		}
	},
	complete : function(xhr) {
		if (xhr.status == 400) {
			window.location.href = config.protocol() + '//' + config.host() + config.ctx;
		}
	}
});