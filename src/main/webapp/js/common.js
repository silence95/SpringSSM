var request = {
	getParameter : function(val) {
		var uri = window.location.search;
		var start = uri.indexOf(val);
		if(start == -1)
			return "";
		start += val.length + 1;
		var end = uri.indexOf("&",start);
		if(end == -1)
			return uri.substring(start);
		return uri.substring(start, end);
	}
};

function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}