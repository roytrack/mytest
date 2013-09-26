function user_c(F){
var G=encodeURIComponent(window.document.location.href),E="",D="",A="",B="",C=window["BD_PS_C"+(new Date()).getTime()]=new Image();for(v in F){
switch(v){
case"title":A=encodeURIComponent(F[v].replace(/<[^<>]+>/g,""));break;case"url":A=encodeURIComponent(F[v]);break;default:A=F[v]}E+=v+"="+A+"&"}B="&mu="+G;C.src="http://nsclick.baidu.com/v.gif?pid=201&pj=psuser&"+E+"path="+G+"&wd="+D+"&t="+new Date().getTime();return true}function Dialog(B){
var h=window,q=document.body,o=document.documentElement;var I=baidu.dom.create("div");var f=B.width||395,n=B.height||400;var R,Q,S,X,T=8;var C=null;var i=B.closeFunc||function(){
};var V,M,K;function k(){
A()}function U(){
q.appendChild(I);I.style.display="block";if(B.load){
B.load.call(window)}G(true)}function g(b){
try{
I.style.display="none"}catch(d){
}if(!b&&C){
try{
C.call(window,0)}catch(d){
}C=null}}function G(b){
if(I.sbIE6){
I.sbIE6.redraw()}if(b&&I.msk){
I.msk.redraw()}if(I.dlg){
I.dlg.redraw()}if(I.shd){
I.shd.redraw()}}function L(b,d){
f=b;n=d;if(I.dlg){
I.dlg.resize(b,d)}if(I.shd){
I.shd.resize(b,d)}}function A(){
I.innerHTML="";if(isIE){
I.sbIE6=e();I.appendChild(I.sbIE6)}if(B.mask){
I.msk=J();I.appendChild(I.msk)}I.dlg=E("登录");I.appendChild(I.dlg);if(B.shadow){
I.shd=l();I.appendChild(I.shd)}baidu.on(h,"resize",function(){
G(true)})}function H(d){
d=h.event||d;var b=r(d.clientX-R,0,S);var s=r(d.clientY-Q,0,X);baidu.setStyle(I.dlg,"left",b+"px");baidu.setStyle(I.dlg,"top",s+"px");if(I.shd){
baidu.setStyle(I.shd,"left",b+T+"px");baidu.setStyle(I.shd,"top",s+T+"px")}}function m(){
baidu.un(q,"mousemove",H);baidu.un(q,"mouseup",m);if(I.dlg.releaseCapture){
I.dlg.releaseCapture()}if(h.releaseEvents){
h.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP)}}function F(){
if(B.ready){
B.ready.call(window)}}function J(){
var b=D();var d=c("#333",40,b);d.redraw=function(){
var s=D(true);Z(d,s);setTimeout(function(){
var t=D();Z(d,t)},0)};return d}function e(){
var b=D();var d=baidu.dom.create("iframe");baidu.setStyle(d,"position","absolute");baidu.setStyle(d,"top",b.y+"px");baidu.setStyle(d,"left",b.x+"px");baidu.setStyle(d,"zIndex",10000);baidu.setStyle(d,"opacity",1/100);baidu.setStyle(d,"filter","alpha(opacity=1)");baidu.setStyle(d,"width",b.w+"px");baidu.setStyle(d,"height",b.h+"px");d.redraw=function(){
var s=D(true);baidu.setStyle(d,"width",s.w+"px");baidu.setStyle(d,"height",s.h+"px");setTimeout(function(){
var t=D();baidu.setStyle(d,"width",t.w+"px");baidu.setStyle(d,"height",t.h+"px")},0)};return d}function D(d){
var b=d?Y():N();b.x=0;b.y=0;b.z=10001;return b}function l(){
var b=c("#333",20,O());b.redraw=function(){
var d=O();Z(b,d)};b.resize=p;return b}function E(t){
var b=c("",100,O(true));b.className="bd_dialog";M=baidu.dom.create("div");M.className="bd_dialog_handle";K=baidu.dom.create("span");K.className="bd_dialog_title";K.innerHTML=t;M.appendChild(K);var d=baidu.dom.create("span");d.className="bd_dialog_close";baidu.on(d,"click",function(){
i();g()});M.appendChild(d);var s=baidu.dom.create("span");baidu.setStyle(s,"clear","both");M.appendChild(s);if(B.drag){
baidu.on(M,"mousedown",function(u){
u=h.event||u;var w=N();R=u.clientX-b.offsetLeft;Q=u.clientY-b.offsetTop;S=w.w-b.clientWidth-T;X=w.h-b.clientHeight-T;baidu.on(q,"mousemove",H);baidu.on(q,"mouseup",m);baidu.on(h,"scroll",m);if(b.setCapture){
b.setCapture()}else{
baidu.on(h,"mouseup",m)}if(h.captureEvents){
h.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP)}})}V=baidu.dom.create("div");V.className="bd_dialog_main";if(B.domNode){
V.appendChild(B.domNode)}b.caption=function(u){
if(u){
K.innerHTML=u}return K.innerHTML};b.clear=function(){
V.innerHTML=""};b.redraw=function(){
var u=O(true);Z(b,u)};b.resize=p;b.appendChild(M);b.appendChild(V);j(n);return b}function j(b){
V.style.height=b-40+"px"}function c(d,s,b){
var t=baidu.dom.create("div");baidu.setStyle(t,"position","absolute");baidu.setStyle(t,"top",b.y+"px");baidu.setStyle(t,"left",b.x+"px");baidu.setStyle(t,"zIndex",b.z);baidu.setStyle(t,"backgroundColor",d);baidu.setStyle(t,"opacity",s/100);baidu.setStyle(t,"width",b.w+"px");baidu.setStyle(t,"height",b.h+"px");return t}function p(d,t){
var z=N();maxX=z.w-d-T;maxY=z.h-t-T;var s=d-parseInt(baidu.getStyle(this,"width"));var u=t-parseInt(baidu.getStyle(this,"height"));var b=r(parseInt(baidu.getStyle(this,"left"))-s/2,0,maxX);var AA=r(parseInt(baidu.getStyle(this,"top"))-u/2,0,maxY);baidu.setStyle(this,"top",AA+"px");baidu.setStyle(this,"left",b+"px");baidu.setStyle(this,"width",d+"px");baidu.setStyle(this,"height",t+"px");j(t)}function Z(d,b){
baidu.setStyle(d,"top",b.y+"px");baidu.setStyle(d,"left",b.x+"px");baidu.setStyle(d,"width",b.w+"px");baidu.setStyle(d,"height",b.h+"px")}function O(s){
var b=Y();var d=s?0:T;return{
z:s?10005:10002,x:r((b.w-f)/2,0)+d+b.l,y:r((b.h-n)/2,0)+d+b.t,w:f,h:n}}function r(d,s,b){
if(b){
d=d>b?b:d}return d>=s?d:s}function N(d){
var s=Math.max(q.scrollHeight,o.scrollHeight);var b=Math.max(q.scrollWidth,o.scrollWidth);if(o&&o.clientWidth){
s=Math.max(o.clientHeight,s);b=Math.max(o.clientWidth,b)}else{
s=Math.max(q.clientHeight,s);b=Math.max(q.clientWidth,b)}return{
h:s,w:b}}function Y(){
var b,d;if(o&&o.clientWidth){
b=o.clientWidth;d=o.clientHeight}else{
b=q.clientWidth;d=q.clientHeight}return{
w:b,h:d,t:Math.max(q.scrollTop,o.scrollTop),l:Math.max(q.scrollLeft,o.scrollLeft)}}function a(b){
if(I.dlg){
I.dlg.caption(b)}}function W(b){
C=b}function P(b){
K.innerHTML=b}k();return{
resize:L,setCloseCallback:W,caption:a,ready:F,show:U,close:g,domNode:I,setTitle:P}}bds.se.login={
dialog:null,closeFunc:null,succFunc:null,fillUsername:false,loginUrl:"http://www.baidu.com/cache/user/html/login.html",fillUrl:"http://www.baidu.com/cache/user/html/fillusername.html",addStyle:function(B){
if(baidu.ie){
var C=document.createStyleSheet();C.cssText=B}else{
var A=document.createElement("style");A.type="text/css";A.appendChild(document.createTextNode(B));document.getElementsByTagName("HEAD")[0].appendChild(A)}},init:function(){
this.initStyle();this.initLogin();this.setUserInfo();var A=this;bds.comm.loginAction.push(function(B,C){
A.setUserInfo(C)})},initStyle:function(){
var A=[".bd_dialog{
border:2px solid #a8b9eb;background:#dae4ff;color:#333;overflow:hidden}",".bd_dialog_handle{
width:100%;height:30px;overflow:hidden;background:url(http://www.baidu.com/cache/user/img/login.png) repeat-x 0 -155px;cursor:move;-moz-user-select:none}",".bd_dialog_title{
line-height:24px;font-size:14px;font-weight:bold;float:left;overflow:hidden;margin:3px 10px}",".bd_dialog_close{
width:19px;height:19px;float:right;background:url(http://www.baidu.com/cache/user/img/login.png) -177px -81px;overflow:hidden;margin:6px;cursor:pointer}",".bd_dialog_main{
width:auto;height:auto;margin:5px;border:1px solid #c3cff2;overflow:hidden;background:#FFF}"];this.addStyle(A.join(""))},initLogin:function(){
var B=document.createElement("DIV");B.style.height="100%";B.innerHTML='<iframe id="login_iframe" width="100%" height="100%" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="about:blank"></iframe>';var A={
domNode:B,width:390,height:380,mask:true,shadow:true,drag:true,closeFunc:function(){
user_c({
fm:"set",tab:"close"})}};this.dialog=new Dialog(A)},setUserInfo:function(B){
var A=B||bds.comm.user;if(!A){
return }var I=baidu.g("u");I.innerHTML="<span id='user'><strong class='un'>"+A+"</strong><div id='userMenu'><ul><li><a href='http://passport.baidu.com' onmousedown=\"return user_c({
'fm':'set','tab':'username'})\">个人资料</a></li><li><a href='http://www.baidu.com/home/page/show/setting' onmousedown=\"return user_c({
'fm':'set','tab':'msg'})\">首页设置</a></li><li><a href='http://www.baidu.com/gaoji/preferences.html' onmousedown=\"return user_c({
'fm':'set','tab':'setting','login':'1'})\">搜索设置</a></li><li class='nl'><a href='http://passport.baidu.com/?logout&tpl=mn' onmousedown=\"return user_c({
'fm':'set','tab':'logout'})\">退出</a></li></ul></div></span>|<a href='/' class='last' onmousedown=\"return user_c({
'fm':'set','tab':'index','login':'1'})\">百度首页</a>";var E=[{
text:"个人资料",url:"http://passport.baidu.com",tab:"username"},{
text:"首页设置",url:"http://www.baidu.com/home/page/show/setting",tab:"msg"},{
text:"搜索设置",url:"http://www.baidu.com/gaoji/preferences.html",tab:"setting"},{
text:"退出",url:"http://passport.baidu.com/?logout&tpl=mn",tab:"logout"}];var A=baidu.g("user");var J=baidu.g("userMenu");if(A&&J){
var G=baidu.dom.create("ul");var D=[];var F=E.length;for(var C=0;C<F;C++){
var H=E[C];D.push("<li "+((C==F-1)?"class='nl'":"")+"><a  href='"+H.url+"' onmousedown=\"return user_c({
'fm':'set','tab':'"+H.tab+"','url':this.href,'login':'1'})\">"+H.text+"</a></li>")}G.innerHTML=D.join("");J.innerHTML="";J.appendChild(G);baidu.on(A,"mouseover",function(K){
J.style.display="block"},true);baidu.on(A,"mouseout",function(K){
J.style.display="none"},true)}},resize:function(A,B){
this.dialog.resize(A,B)},setIframeUrl:function(A){
baidu.g("login_iframe").src=A},open:function(D,A){
var B=this.loginUrl;var C=false;if(bds.comm.user!=""&&A){
B=this.fillUrl;C=true}this.fillUsername=A||false;this.dialog.show();if(C){
this.dialog.setTitle("填写用户名")}this.setIframeUrl(B);this.succFunc=D||function(){
window.document.location.reload(true)};this.closeFunc=D||function(){
};this.dialog.setCloseCallback(this.closeFunc)},close:function(){
this.dialog.close(true)},success:function(A,D,B){
if(this.fillUsername&&D==""){
this.setIframeUrl(this.fillUrl);this.dialog.setTitle("填写用户名");var C=this;setTimeout(function(){
C.setComm(A,D);C.runLoginAction(A)},20);user_c({
fm:"set",tab:"showfill"})}else{
user_c({
fm:"set",tab:"loginOK",type:B});var C=this;setTimeout(function(){
C.setComm(A,D);C.succFunc.call(window,1,A);C.runLoginAction(A);C.close()},20)}},runLoginAction:function(A){
for(var B=0;B<bds.comm.loginAction.length;B++){
bds.comm.loginAction[B].call(window,1,A)}},setComm:function(A,B){
if(!bds.comm){
return }bds.comm.user=A;bds.comm.username=B;window.bdUser=A},tongji:function(A){
user_c({
fm:"set",tab:A})}};bds.se.login.init();window._invoke_login=function(B,A){
bds.se.login.open(B,A)};if(baidu.g("lb")){
baidu.on("lb","mousedown",function(){
bds.se.login.open()})};