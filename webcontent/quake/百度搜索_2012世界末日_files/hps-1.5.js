var sethfPos=sethfPos||0;(function(){
var M="http://www.baidu.com/",E=navigator.userAgent.indexOf("MSIE")!=-1&&!window.opera,H=Math.random()*100,N="百度一下，你就知道",F="";window.fa=function(P){
try{
if(window.sidebar){
window.sidebar.addPanel(N,M,"")}else{
if(window.opera&&window.print){
P.setAttribute("rel","sidebar");P.setAttribute("href",M);P.setAttribute("title",N);P.click()}else{
window.external.AddFavorite(M,N)}}}catch(O){
}};function G(P){
var O=P.parentNode;if(O){
O.style.marginBottom="20px";O.style.marginTop="2px"}}if(E){
try{
var K=/se /gi.test(navigator.userAgent);var L=false;try{
L=+external.twGetVersion(external.twGetSecurityID(window)).replace(/\./g,"")>1013}catch(J){
}if(K||L){
var D=sethfPos?document.getElementById("set_f"):document.getElementById("setf");if(D){
D.style.display="inline";if(sethfPos){
G(D);F="favorites"}}}else{
var A=sethfPos?document.getElementById("set_h"):document.getElementById("seth");var C=A&&A.isHomePage(M);if(!C){
var A=sethfPos?document.getElementById("set_h"):document.getElementById("seth");if(A){
A.style.display="inline";if(sethfPos){
G(A);F="home"}}}else{
if(sethfPos){
F="home_exist"}}if(H<=1){
var B=encodeURIComponent(window.document.location.href),I=window["BD_PS_C"+(new Date()).getTime()]=new Image();I.src="http://nsclick.baidu.com/v.gif?pid=201&pj=hps&hp="+C+"&path="+B+"&t="+new Date().getTime();return true}}}catch(J){
}}else{
try{
if(window.sidebar||(window.opera&&window.print)){
var D=sethfPos?document.getElementById("set_f"):document.getElementById("setf");if(D){
if(D.tagName=="SPAN"||D.tagName=="span"){
D.getElementsByTagName("a")[0].innerHTML="把百度加为书签"}else{
D.innerHTML="把百度加为书签"}D.style.display="inline";if(sethfPos){
G(D);F="favorites"}}}}catch(J){
}}if(F&&sethfPos){
ns_c({
fm:"sethf_show",tab:F})}})();