var T,baidu=T=baidu||{
version:"1.3.4"};baidu.guid="$BAIDU$";window[baidu.guid]=window[baidu.guid]||{
};baidu.ajax=baidu.ajax||{
};baidu.fn=baidu.fn||{
};baidu.fn.blank=function(){
};baidu.ajax.request=function(L,A){
A=A||{
};var G=A.data||"",I=!(A.async===false),H=A.username||"",C=A.password||"",O=(A.method||"GET").toUpperCase(),J=A.headers||{
},M={
},D,B;function F(){
if(B.readyState==4){
try{
var P=B.status}catch(Q){
K("failure");return }K(P);if((P>=200&&P<300)||P==304||P==1223){
K("success")}else{
K("failure")}window.setTimeout(function(){
B.onreadystatechange=baidu.fn.blank;if(I){
B=null}},0)}}function N(){
if(window.ActiveXObject){
try{
return new ActiveXObject("Msxml2.XMLHTTP")}catch(P){
try{
return new ActiveXObject("Microsoft.XMLHTTP")}catch(P){
}}}if(window.XMLHttpRequest){
return new XMLHttpRequest()}}function K(Q){
Q="on"+Q;var R=M[Q],P=baidu.ajax[Q];if(R){
if(Q!="onsuccess"){
R(B)}else{
R(B,B.responseText)}}else{
if(P){
if(Q=="onsuccess"){
return }P(B)}}}for(D in A){
M[D]=A[D]}J["X-Request-With"]="XMLHttpRequest";try{
B=N();if(O=="GET"){
if(G){
L+=(L.indexOf("?")>=0?"&":"?")+G;G=null}if(A.noCache){
L+="&b"+(new Date()).getTime()+"=1"}}if(H){
B.open(O,L,I,H,C)}else{
B.open(O,L,I)}if(I){
B.onreadystatechange=F}if(O=="POST"){
B.setRequestHeader("Content-Type","application/x-www-form-urlencoded")}for(D in J){
if(J.hasOwnProperty(D)){
B.setRequestHeader(D,J[D])}}K("beforerequest");B.send(G);if(!I){
F()}}catch(E){
K("failure")}return B};baidu.ajax.post=function(A,C,B){
return baidu.ajax.request(A,{
onsuccess:B,method:"POST",data:C})};baidu.ajax.get=function(A,B){
return baidu.ajax.request(A,{
onsuccess:B})};baidu.url=baidu.url||{
};baidu.lang=baidu.lang||{
};baidu.lang.isArray=function(A){
return"[object Array]"==Object.prototype.toString.call(A)};baidu.url.queryToJson=function(J){
var E=J.substr(J.lastIndexOf("?")+1),H=E.split("&"),F=H.length,A={
},G=0,C,D,B,I;for(;G<F;G++){
I=H[G].split("=");C=I[0];D=I[1];B=A[C];if("undefined"==typeof B){
A[C]=D}else{
if(baidu.lang.isArray(B)){
B.push(D)}else{
A[C]=[B,D]}}}return A};baidu.url.escapeSymbol=function(A){
return String(A).replace(/\%/g,"%25").replace(/&/g,"%26").replace(/\+/g,"%2B").replace(/\ /g,"%20").replace(/\//g,"%2F").replace(/\#/g,"%23").replace(/\=/g,"%3D")};baidu.object=baidu.object||{
};baidu.object.each=function(C,E){
var A,B,D;if("function"==typeof E){
for(B in C){
if(C.hasOwnProperty(B)){
D=C[B];A=E.call(C,D,B);if(A===false){
break}}}}return C};baidu.url.jsonToQuery=function(E,C){
var B=[],D,A=C||function(F){
return baidu.url.escapeSymbol(F)};baidu.object.each(E,function(F,G){
if(baidu.lang.isArray(F)){
D=F.length;while(D--){
B.push(G+"="+A(F[D],G))}}else{
B.push(G+"="+A(F,G))}});return B.join("&")};baidu.string=baidu.string||{
};baidu.string.encodeHTML=function(A){
return String(A).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#39;")};baidu.encodeHTML=baidu.string.encodeHTML;baidu.string.decodeHTML=function(B){
var A=String(B).replace(/&quot;/g,'"').replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&");return A.replace(/&#([\d]+);/g,function(C,D){
return String.fromCharCode(parseInt(D,10))})};baidu.decodeHTML=baidu.string.decodeHTML;baidu.string.getByteLength=function(A){
return String(A).replace(/[^\x00-\xff]/g,"ci").length};baidu.string.format=function(D,B){
D=String(D);var A=Array.prototype.slice.call(arguments,1),C=Object.prototype.toString;if(A.length){
A=A.length==1?(B!==null&&(/\[object Array\]|\[object Object\]/.test(C.call(B)))?B:A):A;return D.replace(/#\{
(.+?)\}/g,function(G,E){
var F=A[E];if("[object Function]"==C.call(F)){
F=F(E)}return("undefined"==typeof F?"":F)})}return D};baidu.format=baidu.string.format;baidu.string.subByte=function(A,B){
A=String(A);if(B<0||baidu.string.getByteLength(A)<=B){
return A}A=A.substr(0,B).replace(/([^\x00-\xff])/g,"\x241 ").substr(0,B).replace(/[^\x00-\xff]$/,"").replace(/([^\x00-\xff]) /g,"\x241");return A};baidu.lang.isObject=function(A){
return"function"==typeof A||!!(A&&"object"==typeof A)};baidu.isObject=baidu.lang.isObject;baidu.object.clone=(function(A){
return function(C){
var F=C,E,B;if(!C||C instanceof Number||C instanceof String||C instanceof Boolean){
return F}else{
if(baidu.lang.isArray(C)){
F=[];var D=0;for(E=0,B=C.length;E<B;E++){
F[D++]=baidu.object.clone(C[E])}}else{
if(baidu.lang.isObject(C)){
if(A[Object.prototype.toString.call(C)]){
return F}F={
};for(E in C){
if(C.hasOwnProperty(E)){
F[E]=baidu.object.clone(C[E])}}}}}return F}})({
"[object Function]":1,"[object RegExp]":1,"[object Date]":1,"[object Error]":1});baidu.extend=baidu.object.extend=function(C,B){
for(var A in B){
if(B.hasOwnProperty(A)){
C[A]=B[A]}}return C};baidu.page=baidu.page||{
};baidu.page.getViewWidth=function(){
var A=document,B=A.compatMode=="BackCompat"?A.body:A.documentElement;return B.clientWidth};baidu.page.getHeight=function(){
var C=document,B=C.body,D=C.documentElement,A=C.compatMode=="BackCompat"?B:C.documentElement;return Math.max(D.scrollHeight,B.scrollHeight,A.clientHeight)};baidu.page.getScrollTop=function(){
var A=document;return window.pageYOffset||A.documentElement.scrollTop||A.body.scrollTop};baidu.page.getWidth=function(){
var C=document,B=C.body,D=C.documentElement,A=C.compatMode=="BackCompat"?B:C.documentElement;return Math.max(D.scrollWidth,B.scrollWidth,A.clientWidth)};baidu.page.getViewHeight=function(){
var A=document,B=A.compatMode=="BackCompat"?A.body:A.documentElement;return B.clientHeight};baidu.dom=baidu.dom||{
};baidu.dom.g=function(A){
if("string"==typeof A||A instanceof String){
return document.getElementById(A)}else{
if(A&&A.nodeName&&(A.nodeType==1||A.nodeType==9)){
return A}}return null};baidu.g=baidu.G=baidu.dom.g;baidu.dom._matchNode=function(B,D,C){
B=baidu.dom.g(B);for(var A=B[C];A;A=A[D]){
if(A.nodeType==1){
return A}}return null};baidu.dom.first=function(A){
return baidu.dom._matchNode(A,"nextSibling","firstChild")};baidu.lang.isString=function(A){
return"[object String]"==Object.prototype.toString.call(A)};baidu.isString=baidu.lang.isString;baidu.dom._g=function(A){
if(baidu.lang.isString(A)){
return document.getElementById(A)}return A};baidu._g=baidu.dom._g;baidu.dom.getDocument=function(A){
A=baidu.dom.g(A);return A.nodeType==9?A:A.ownerDocument||A.document};baidu.browser=baidu.browser||{
};if(/msie (\d+\.\d)/i.test(navigator.userAgent)){
baidu.browser.ie=baidu.ie=document.documentMode||+RegExp["\x241"]}baidu.dom.getComputedStyle=function(A,B){
A=baidu.dom._g(A);var C=baidu.dom.getDocument(A),D;if(C.defaultView&&C.defaultView.getComputedStyle){
D=C.defaultView.getComputedStyle(A,null);if(D){
return D[B]||D.getPropertyValue(B)}}return""};baidu.dom._styleFixer=baidu.dom._styleFixer||{
};baidu.dom._styleFilter=baidu.dom._styleFilter||[];baidu.dom._styleFilter.filter=function(A,D,C){
for(var B=0,E=baidu.dom._styleFilter,F;F=E[B];B++){
if(F=F[C]){
D=F(A,D)}}return D};baidu.string.toCamelCase=function(A){
if(A.indexOf("-")<0&&A.indexOf("_")<0){
return A}return A.replace(/[-_][^-_]/g,function(B){
return B.charAt(1).toUpperCase()})};baidu.dom.getStyle=function(E,A){
var C=baidu.dom;E=C.g(E);A=baidu.string.toCamelCase(A);var D=E.style[A]||(E.currentStyle?E.currentStyle[A]:"")||C.getComputedStyle(E,A);if(!D){
var B=C._styleFixer[A];D=B&&B.get?B.get(E):style[B||A]}if(B=C._styleFilter){
D=B.filter(A,D,"get")}return D};baidu.getStyle=baidu.dom.getStyle;baidu.dom.setStyle=function(E,A,D){
var C=baidu.dom,B;E=C.g(E);A=baidu.string.toCamelCase(A);if(B=C._styleFilter){
D=B.filter(A,D,"set")}B=C._styleFixer[A];(B&&B.set)?B.set(E,D):(E.style[B||A]=D);return E};baidu.setStyle=baidu.dom.setStyle;baidu.dom.hasAttr=function(C,A){
C=baidu.g(C);var B=C.attributes.getNamedItem(A);return !!(B&&B.specified)};baidu.dom.getAncestorBy=function(B,A){
B=baidu.dom.g(B);while((B=B.parentNode)&&B.nodeType==1){
if(A(B)){
return B}}return null};(function(){
var A=new RegExp("(^[\\s\\t\\xa0\\u3000]+)|([\\u3000\\xa0\\s\\t]+\x24)","g");baidu.string.trim=function(B){
return String(B).replace(A,"")}})();baidu.trim=baidu.string.trim;baidu.dom.hasClass=function(D,C){
D=baidu.dom.g(D);var A=baidu.string.trim(C).split(/\s+/),B=A.length;C=D.className.split(/\s+/).join(" ");while(B--){
if(!(new RegExp("(^| )"+A[B]+"( |\x24)")).test(C)){
return false}}return true};baidu.dom._NAME_ATTRS=(function(){
var A={
cellpadding:"cellPadding",cellspacing:"cellSpacing",colspan:"colSpan",rowspan:"rowSpan",valign:"vAlign",usemap:"useMap",frameborder:"frameBorder"};if(baidu.browser.ie<8){
A["for"]="htmlFor";A["class"]="className"}else{
A.htmlFor="for";A.className="class"}return A})();baidu.dom.setAttr=function(A,B,C){
A=baidu.dom.g(A);if("style"==B){
A.style.cssText=C}else{
B=baidu.dom._NAME_ATTRS[B]||B;A.setAttribute(B,C)}return A};baidu.setAttr=baidu.dom.setAttr;baidu.dom.setAttrs=function(C,B){
C=baidu.dom.g(C);for(var A in B){
baidu.dom.setAttr(C,A,B[A])}return C};baidu.setAttrs=baidu.dom.setAttrs;baidu.dom.create=function(D,B){
var C=document.createElement(D),A=B||{
};return baidu.dom.setAttrs(C,A)};baidu.dom.insertBefore=function(C,D){
var A,B;A=baidu.dom._g;C=A(C);D=A(D);B=D.parentNode;if(B){
B.insertBefore(C,D)}return C};baidu.dom.insertAfter=function(C,D){
var A,B;A=baidu.dom._g;C=A(C);D=A(D);B=D.parentNode;if(B){
B.insertBefore(C,D.nextSibling)}return C};baidu.string.escapeReg=function(A){
return String(A).replace(new RegExp("([.*+?^=!:\x24{
}()|[\\]/\\\\])","g"),"\\\x241")};baidu.dom.q=function(B,E,H){
var A=[],F=baidu.string.trim,C,D,I,G;if(!(B=F(B))){
return A}if("undefined"==typeof E){
E=document}else{
E=baidu.dom.g(E);if(!E){
return A}}H&&(H=F(H).toUpperCase());if(E.getElementsByClassName){
I=E.getElementsByClassName(B);C=I.length;for(D=0;D<C;D++){
G=I[D];if(H&&G.tagName!=H){
continue}A[A.length]=G}}else{
B=new RegExp("(^|\\s)"+baidu.string.escapeReg(B)+"(\\s|\x24)");I=H?E.getElementsByTagName(H):(E.all||E.getElementsByTagName("*"));C=I.length;for(D=0;D<C;D++){
G=I[D];B.test(G.className)&&(A[A.length]=G)}}return A};baidu.q=baidu.Q=baidu.dom.q;baidu.dom._styleFixer["float"]=baidu.browser.ie?"styleFloat":"cssFloat";baidu.dom._styleFixer.opacity=baidu.browser.ie?{
get:function(B){
var A=B.style.filter;return A&&A.indexOf("opacity=")>=0?(parseFloat(A.match(/opacity=([^)]*)/)[1])/100)+"":"1"},set:function(B,C){
var A=B.style;A.filter=(A.filter||"").replace(/alpha\([^\)]*\)/gi,"")+(C==1?"":"alpha(opacity="+C*100+")");A.zoom=1}}:null;baidu.dom.getAttr=function(A,B){
A=baidu.dom.g(A);if("style"==B){
return A.style.cssText}B=baidu.dom._NAME_ATTRS[B]||B;return A.getAttribute(B)};baidu.getAttr=baidu.dom.getAttr;baidu.dom.removeClass=function(E,D){
E=baidu.dom.g(E);var G=E.className.split(/\s+/),C=D.split(/\s+/),A,B=C.length,H,F=0;for(;F<B;++F){
for(H=0,A=G.length;H<A;++H){
if(G[H]==C[F]){
G.splice(H,1);break}}}E.className=G.join(" ");return E};baidu.removeClass=baidu.dom.removeClass;baidu.dom.setStyles=function(A,C){
A=baidu.dom.g(A);for(var B in C){
baidu.dom.setStyle(A,B,C[B])}return A};baidu.setStyles=baidu.dom.setStyles;baidu.dom.insertHTML=function(D,B,E){
D=baidu.dom.g(D);var A,C;if(D.insertAdjacentHTML){
D.insertAdjacentHTML(B,E)}else{
A=D.ownerDocument.createRange();B=B.toUpperCase();if(B=="AFTERBEGIN"||B=="BEFOREEND"){
A.selectNodeContents(D);A.collapse(B=="AFTERBEGIN")}else{
C=B=="BEFOREBEGIN";A[C?"setStartBefore":"setEndAfter"](D);A.collapse(C)}A.insertNode(A.createContextualFragment(E))}return D};baidu.insertHTML=baidu.dom.insertHTML;baidu.dom.getText=function(D){
var A="",C,E=0,B;D=baidu._g(D);if(D.nodeType===3||D.nodeType===4){
A+=D.nodeValue}else{
if(D.nodeType!==8){
C=D.childNodes;for(B=C.length;E<B;E++){
A+=baidu.dom.getText(C[E])}}}return A};baidu.dom._styleFilter[baidu.dom._styleFilter.length]={
set:function(B,A){
if(A.constructor==Number&&!/zIndex|fontWeight|opacity|zoom|lineHeight/i.test(B)){
A=A+"px"}return A}};baidu.dom._styleFilter[baidu.dom._styleFilter.length]={
get:function(E,D){
if(/color/i.test(E)&&D.indexOf("rgb(")!=-1){
var C=D.split(",");D="#";for(var A=0,B;B=C[A];A++){
B=parseInt(B.replace(/[^\d]/gi,""),10).toString(16);D+=B.length==1?"0"+B:B}D=D.toUpperCase()}return D}};baidu.dom.addClass=function(D,C){
D=baidu.dom.g(D);var A=C.split(/\s+/),B=D.className,E=" "+B+" ",F=0,G=A.length;for(;F<G;F++){
if(E.indexOf(" "+A[F]+" ")<0){
B+=" "+A[F]}}D.className=B;return D};baidu.addClass=baidu.dom.addClass;baidu.dom.children=function(A){
A=baidu.dom.g(A);for(var B=[],C=A.firstChild;C;C=C.nextSibling){
if(C.nodeType==1){
B.push(C)}}return B};if(/opera\/(\d+\.\d)/i.test(navigator.userAgent)){
baidu.browser.opera=+RegExp["\x241"]}baidu.browser.isWebkit=/webkit/i.test(navigator.userAgent);baidu.browser.isGecko=/gecko/i.test(navigator.userAgent)&&!/like gecko/i.test(navigator.userAgent);baidu.browser.isStrict=document.compatMode=="CSS1Compat";baidu.dom.getPosition=function(L){
L=baidu.dom.g(L);var C=baidu.dom.getDocument(L),I=baidu.browser,F=baidu.dom.getStyle,J=I.isGecko>0&&C.getBoxObjectFor&&F(L,"position")=="absolute"&&(L.style.top===""||L.style.left===""),E={
left:0,top:0},G=(I.ie&&!I.isStrict)?C.body:C.documentElement,B,K;if(L==G){
return E}if(L.getBoundingClientRect){
K=L.getBoundingClientRect();E.left=Math.floor(K.left)+Math.max(C.documentElement.scrollLeft,C.body.scrollLeft);E.top=Math.floor(K.top)+Math.max(C.documentElement.scrollTop,C.body.scrollTop);E.left-=C.documentElement.clientLeft;E.top-=C.documentElement.clientTop;var D=C.body,A=parseInt(F(D,"borderLeftWidth")),H=parseInt(F(D,"borderTopWidth"));if(I.ie&&!I.isStrict){
E.left-=isNaN(A)?2:A;E.top-=isNaN(H)?2:H}}else{
B=L;do{
E.left+=B.offsetLeft;E.top+=B.offsetTop;if(I.isWebkit>0&&F(B,"position")=="fixed"){
E.left+=C.body.scrollLeft;E.top+=C.body.scrollTop;break}B=B.offsetParent}while(B&&B!=L);if(I.opera>0||(I.isWebkit>0&&F(L,"position")=="absolute")){
E.top-=C.body.offsetTop}B=L.offsetParent;while(B&&B!=C.body){
E.left-=B.scrollLeft;if(!I.opera||B.tagName!="TR"){
E.top-=B.scrollTop}B=B.offsetParent}}return E};baidu.cookie=baidu.cookie||{
};baidu.cookie._isValidKey=function(A){
return(new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{
\\}\\/\\u0080-\\uffff]+\x24')).test(A)};baidu.cookie.setRaw=function(D,C,A){
if(!baidu.cookie._isValidKey(D)){
return }A=A||{
};var B=A.expires;if("number"==typeof A.expires){
B=new Date();B.setTime(B.getTime()+A.expires)}document.cookie=D+"="+C+(A.path?"; path="+A.path:"")+(B?"; expires="+B.toGMTString():"")+(A.domain?"; domain="+A.domain:"")+(A.secure?"; secure":"")};baidu.cookie.getRaw=function(A){
if(baidu.cookie._isValidKey(A)){
var C=new RegExp("(^| )"+A+"=([^;]*)(;|\x24)"),B=C.exec(document.cookie);if(B){
return B[2]||null}}return null};baidu.cookie.remove=function(A,B){
B=B||{
};B.expires=new Date(0);baidu.cookie.setRaw(A,"",B)};if(/firefox\/(\d+\.\d)/i.test(navigator.userAgent)){
baidu.browser.firefox=+RegExp["\x241"]}baidu.array=baidu.array||{
};baidu.array.each=function(C,E){
var F,D,A,B=C.length;if("function"==typeof E){
for(A=0;A<B;A++){
D=C[A];F=E.call(C,D,A);if(F===false){
break}}}return C};baidu.each=baidu.array.each;baidu.array.hash=function(D,A){
var C={
},E=A&&A.length,F=0,B=D.length;for(;F<B;F++){
C[D[F]]=(E&&E>F)?A[F]:true}return C};baidu.json=baidu.json||{
};baidu.json.parse=function(A){
return(new Function("return "+A))()};baidu.json.stringify=(function(){
var A={
"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};function B(F){
if(/["\\\x00-\x1f]/.test(F)){
F=F.replace(/["\\\x00-\x1f]/g,function(H){
var G=A[H];if(G){
return G}G=H.charCodeAt();return"\\u00"+Math.floor(G/16).toString(16)+(G%16).toString(16)})}return'"'+F+'"'}function D(F){
var J=["["],I=F.length,K,H,G;for(H=0;H<I;H++){
G=F[H];switch(typeof G){
case"undefined":case"function":case"unknown":break;default:if(K){
J.push(",")}J.push(baidu.json.stringify(G));K=1}}J.push("]");return J.join("")}function E(F){
return F<10?"0"+F:F}function C(F){
return'"'+F.getFullYear()+"-"+E(F.getMonth()+1)+"-"+E(F.getDate())+"T"+E(F.getHours())+":"+E(F.getMinutes())+":"+E(F.getSeconds())+'"'}return function(F){
switch(typeof F){
case"undefined":return"undefined";case"number":return isFinite(F)?String(F):"null";case"string":return B(F);case"boolean":return String(F);default:if(F===null){
return"null"}else{
if(F instanceof Array){
return D(F)}else{
if(F instanceof Date){
return C(F)}else{
var J=["{
"],G=baidu.json.stringify,K,H;for(var I in F){
if(Object.prototype.hasOwnProperty.call(F,I)){
H=F[I];switch(typeof H){
case"undefined":case"unknown":case"function":break;default:if(K){
J.push(",")}K=1;J.push(G(I)+":"+G(H))}}}J.push("}");return J.join("")}}}}}})();baidu.event=baidu.event||{
};baidu.event._listeners=baidu.event._listeners||[];baidu.event.un=function(G,D,H){
G=baidu.dom._g(G);D=D.replace(/^on/i,"").toLowerCase();var A=baidu.event._listeners,F=A.length,E=!H,B,C,I;while(F--){
B=A[F];if(B[1]===D&&B[0]===G&&(E||B[2]===H)){
C=B[4];I=B[3];if(G.removeEventListener){
G.removeEventListener(C,I,false)}else{
if(G.detachEvent){
G.detachEvent("on"+C,I)}}A.splice(F,1)}}return G};baidu.un=baidu.event.un;baidu.event.on=function(A,F,D){
F=F.replace(/^on/i,"");A=baidu.dom._g(A);var E=function(I){
D.call(A,I)},B=baidu.event._listeners,G=baidu.event._eventFilter,C,H=F;F=F.toLowerCase();if(G&&G[F]){
C=G[F](A,F,E);H=C.type;E=C.listener}if(A.addEventListener){
A.addEventListener(H,E,false)}else{
if(A.attachEvent){
A.attachEvent("on"+H,E)}}B[B.length]=[A,F,D,E,H];return A};baidu.on=baidu.event.on;baidu.event.preventDefault=function(A){
if(A.preventDefault){
A.preventDefault()}else{
A.returnValue=false}};baidu.event.EventArg=function(E,C){
C=C||window;E=E||C.event;var D=C.document;this.target=(E.target)||E.srcElement;this.keyCode=E.which||E.keyCode;for(var B in E){
var A=E[B];if("function"!=typeof A){
this[B]=A}}if(!this.pageX&&this.pageX!==0){
this.pageX=(E.clientX||0)+(D.documentElement.scrollLeft||D.body.scrollLeft);this.pageY=(E.clientY||0)+(D.documentElement.scrollTop||D.body.scrollTop)}this._event=E};baidu.event.EventArg.prototype.preventDefault=function(){
if(this._event.preventDefault){
this._event.preventDefault()}else{
this._event.returnValue=false}return this};baidu.event.EventArg.prototype.stopPropagation=function(){
if(this._event.stopPropagation){
this._event.stopPropagation()}else{
this._event.cancelBubble=true}return this};baidu.event.EventArg.prototype.stop=function(){
return this.stopPropagation().preventDefault()};baidu.event.get=function(B,A){
return new baidu.event.EventArg(B,A)};baidu.event.getTarget=function(A){
return A.target||A.srcElement};baidu.sio=baidu.sio||{
};baidu.lang.isFunction=function(A){
return"[object Function]"==Object.prototype.toString.call(A)};baidu.sio._removeScriptTag=function(A){
if(A.clearAttributes){
A.clearAttributes()}else{
for(var B in A){
if(A.hasOwnProperty(B)){
delete A[B]}}}if(A&&A.parentNode){
A.parentNode.removeChild(A)}A=null};baidu.sio.callByServer=function(J,C,B){
var E=document.createElement("SCRIPT"),F="bd__cbs__",D,H,A=B||{
},I=A.charset,G=A.queryField||"callback";if(baidu.lang.isFunction(C)){
D=F+Math.floor(Math.random()*2147483648).toString(36);window[D]=function(){
try{
C.apply(window,arguments);window[D]=null;delete window[D]}catch(K){
}finally{
baidu.sio._removeScriptTag(E)}}}else{
D=C}J=J.replace((new RegExp("(\\?|&)callback=[^&]*")),"\x241"+G+"="+D);if(J.search(new RegExp("(\\?|&)"+G+"=/"))<0){
J+=(J.indexOf("?")<0?"?":"&")+G+"="+D}E.setAttribute("type","text/javascript");I&&E.setAttribute("charset",I);E.setAttribute("src",J);document.getElementsByTagName("head")[0].appendChild(E)};baidu.sio.callByBrowser=function(G,A,F){
var E=document.createElement("SCRIPT"),B=0,H=F||{
},C=H.charset,D=A||function(){
};E.onload=E.onreadystatechange=function(){
if(B){
return }var I=E.readyState;if("undefined"==typeof I||I=="loaded"||I=="complete"){
B=1;try{
D()}finally{
baidu.sio._removeScriptTag(E)}}};E.setAttribute("type","text/javascript");C&&E.setAttribute("charset",C);E.setAttribute("src",G);document.getElementsByTagName("head")[0].appendChild(E)};baidu.swf=baidu.swf||{
};baidu.swf.version=(function(){
var C=navigator;if(C.plugins&&C.mimeTypes.length){
var F=C.plugins["Shockwave Flash"];if(F&&F.description){
return F.description.replace(/([a-zA-Z]|\s)+/,"").replace(/(\s)+r/,".")+".0"}}else{
if(window.ActiveXObject&&!window.opera){
for(var A=10;A>=2;A--){
try{
var D=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+A);if(D){
var B=D.GetVariable("$version");return B.replace(/WIN/g,"").replace(/,/g,".")}}catch(E){
}}}}})();baidu.swf.createHTML=function(Q){
Q=Q||{
};var H=baidu.swf.version,J=Q.ver||"6.0.0",K,M,L,N,I,A,P={
},D=baidu.string.encodeHTML;for(N in Q){
P[N]=Q[N]}Q=P;if(H){
H=H.split(".");J=J.split(".");for(L=0;L<3;L++){
K=parseInt(H[L],10);M=parseInt(J[L],10);if(M<K){
break}else{
if(M>K){
return""}}}}else{
return""}var F=Q.vars,G=["classid","codebase","id","width","height","align"];Q.align=Q.align||"middle";Q.classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000";Q.codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0";Q.movie=Q.url||"";delete Q.vars;delete Q.url;if("string"==typeof F){
Q.flashvars=F}else{
var C=[];for(N in F){
A=F[N];if(A){
C.push(N+"="+encodeURIComponent(A))}}Q.flashvars=C.join("&")}var E=["<object "];for(L=0,I=G.length;L<I;L++){
A=G[L];E.push(" ",A,'="',D(Q[A]),'"')}E.push(">");var O={
wmode:1,scale:1,quality:1,play:1,loop:1,menu:1,salign:1,bgcolor:1,base:1,allowscriptaccess:1,allownetworking:1,allowfullscreen:1,seamlesstabbing:1,devicefont:1,swliveconnect:1,flashvars:1,movie:1};for(N in Q){
A=Q[N];N=N.toLowerCase();if(O[N]&&A){
E.push('<param name="'+N+'" value="'+D(A)+'" />')}}Q.src=Q.movie;Q.name=Q.id;delete Q.id;delete Q.movie;delete Q.classid;delete Q.codebase;Q.type="application/x-shockwave-flash";Q.pluginspage="http://www.macromedia.com/go/getflashplayer";E.push("<embed");var B;for(N in Q){
A=Q[N];if(A){
if((new RegExp("^salign\x24","i")).test(N)){
B=A;continue}E.push(" ",N,'="',D(A),'"')}}if(B){
E.push(' salign="',D(B),'"')}E.push("></embed></object>");return E.join("")};baidu.swf.create=function(B,C){
B=B||{
};var A=baidu.swf.createHTML(B)||B.errorMessage||"";if(C&&"string"==typeof C){
C=document.getElementById(C)}if(C){
C.innerHTML=A}else{
document.write(A)}};baidu.array.remove=function(D,C){
var B=D.length,A=C;if("function"!=typeof C){
A=function(E){
return C===E}}while(B--){
if(true===A.call(D,D[B],B)){
D.splice(B,1)}}return D};baidu.lang.toArray=function(A){
if(A===null||A===undefined){
return[]}if(baidu.lang.isArray(A)){
return A}if(typeof A.length!=="number"||typeof A==="string"||baidu.lang.isFunction(A)){
return[A]}if(A.item){
var B=A.length,C=new Array(B);while(B--){
C[B]=A[B]}return C}return[].slice.call(A)};baidu.swf.getMovie=function(C){
var B=document[C],A;return baidu.browser.ie==9?B&&B.length?(A=baidu.array.remove(baidu.lang.toArray(B),function(D){
return D.tagName.toLowerCase()!="embed"})).length==1?A[0]:A:B:B||window[C]};