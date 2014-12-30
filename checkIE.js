function checkIE(){
	var browser=navigator.appName;
	var b_version=navigator.appVersion;
	var version=b_version.split(";");
	var trim_version=version[1].replace(/[ ]/g,"");
	if (brower=="Microsoft Interner Explorer"&&version[1]=="MSIE 6.0")
		document.styleSheets[0].herf="../etc/Css/webie6.css";
	alert(brow);
	alert(versions);
}