void function(V,o){
var af="http://s1.bdstatic.com/r/www/cache/2012cp/images/";var j="http://hot.baidu.com/savetheearth/savetheearth/?a=total&cb=getNOP";var d=500000;var s=0;var W="http://hot.baidu.com/savetheearth";var i=document;var q=i.documentElement;var C=i.body;var U=i.getElementById("out");var am=[];var B=Math.max;var ak=Math.min;var R=Math.sqrt;var G=Math.pow;var I=Math.abs;var ae=Math.round;var X=navigator.userAgent;var O=/msie/i.test(X)&&!document.addEventListener;var r=/msie 6/i.test(X);var E=/chrome/i.test(X);var w=/safari/i.test(X);var N=/firefox/i.test(X);var K=/opera/i.test(X);var ab=O?16:30;var al=40;V.getNOP=function(e){
s=e.total};var ac={
background:4000,forceground:5000,chasm:6000,chip:7000,ticket:8000,text:9000};var Y=function(au,ar){
var ao={
};var an=function(az){
return[].slice.apply(arguments.callee.caller.arguments,[az||1])};var e=function(aB){
var aA=[],az=0;for(var aC in aB){
aA[az++]=ay(aC)+": "+av(aB[aC])+"; "}return aA.join("")};var ay=function(){
var az={
pos:"position",w:"width",h:"height",l:"left",t:"top"};return function(aA){
return az[aA]||aA}}();var av=function(az){
return az-0===az?az+"px":az};var aq=function(aD){
var aB=[];for(var aA=0,az=aD.length,aC;aA<az;aA++){
aC=aD[aA];if(/^=/.test(aC)){
aB[aA]=ax(aC)}else{
if(aC+""===aC){
aB[aA]=aC}else{
aB[aA]=e(aC)}}}return aB.join(";")};var ap=function(az){
ao[az]=aq(an(1))};var ax=function(az){
return ao[az]};var aw=function(az){
if(/^<(\w+)([^>]*)>$/.test(az)){
var az=RegExp.$1,aA=RegExp.$2;return"<"+az+aA+" style='"+aq(an(1))+"'></"+az+">"}};var at=function(az){
az.style.cssText+=";"+aq(an(1))};return function(az,aB){
var aA;if(!az){
throw new Error()}else{
if(/^=/.test(az)){
if(aB===ar){
aA=ax}else{
aA=ap}}else{
if(/^<[^>]+>$/.test(az)){
aA=aw}else{
if(az.nodeType&&az.nodeType!=3){
aA=at}}}}return aA.apply(this,arguments)}}(V);var aa=function(ao,an){
for(var e in an){
ao.style[e]=an[e]}};var n=function(e,ao,an,ap){
var aq=i.createElement(e||"div");an&&Y.apply(null,[aq].concat(an));ao&&ao.appendChild(aq);ap&&ap.push(aq);return aq};var a=function(av,ap,ar){
var e=new Date().getTime();var aq=e+ap;var ao=1/(ar||5);var at=0;var au;var an=setInterval(function(){
var aw=new Date().getTime();var ax=(aw-e)/ap;if(ax>at+ao){
ax=at+ao}at=ax;if(ax<1){
av(ax)}else{
clearInterval(an);av(1);au&&au()}},1);return{
end:function(aw){
au=aw}}};var ad=function(){
return typeof document.body.style.opacity=="string"?function(e,an){
e.style.opacity=an}:function(an,ao){
var e="alpha";ao=ao*100|0;if(~an.style.filter.indexOf(e)){
an.filters.item(e).opacity=ao}else{
an.style.filter+=" alpha(opacity="+ao+")"}}}();var ah=function(ar,ao,an){
var ap=ao*Math.PI/180;var aq=Math.cos(ap);var e=Math.sin(ap);J(ar,aq,-e,e,aq,an||"center center")};var J=function(){
var an,e;if(E||w){
an="-webkit-transform-origin";e="-webkit-transform"}else{
if(N){
an="MozTransformOrigin";e="MozTransform"}else{
if(K){
an="OTransformOrigin";e="OTransform"}}}return O?function(ar,au,at,aq,ap,aw){
var ax="DXImageTransform.Microsoft.Matrix";var ao=ar.style;if(~ao.filter.indexOf(ax)){
var av=ar.filters.item(ax);av.M11=au,av.M12=at,av.M21=aq,av.M22=ap}else{
ao.filter+=" progid:"+ax+"(M11="+au+", M12="+at+", M21="+aq+", M22="+ap+", FilterType='bilinear', SizingMethod='auto expand')"}}:function(av,ar,aq,au,at,ao){
var ap={
};ap[an]=ao||"0 0";ap[e]="matrix("+[ar,au,aq,at].join(",")+",0,0)";aa(av,ap)}}();var M=function(ap,au,aq,at,ar,aw){
var av=aq/2;var e,ao;a(function(az){
az=x(az,0,1,1);var aA=az*Math.PI;var aB=az<0.5?-1:1;var ax=aB*Math.sin(aA)*aw/au;var ay=-aB*Math.cos(aA);if(az>=0.5){
e&&e(),e=null}J(ap,1,ax,0,ay);ax=O?Math.abs(ax):ax;aa(ap,{
left:at-ax*au/4+"px",top:ar+av*(1-ay)+"px"})},1200,24).end(function(){
ao&&ao()});var an={
center:function(ax){
e=ax;return an},end:function(ax){
ao=ax;return an}};return an};var l=function(ap,e,ao){
var an=function(){
ao.apply(ap,arguments)};if(V.attachEvent){
ap.attachEvent("on"+e,an)}else{
if(V.addEventListener){
ap.addEventListener(e,an,false)}else{
ap["on"+e]=an}}return an};var ag=function(ao,e){
var aq,ap,an;aq=ao.parentNode;ap=e.parentNode;an=document.createTextNode("");aq.insertBefore(an,ao);ap.insertBefore(ao,e);aq.insertBefore(e,an);aq.removeChild(an)};Y("=abs","position: absolute;");Y("=thide","top: -5000px;");Y("=hide","overflow: hidden");Y("=chip","=abs","=hide","background: url("+af+"all.png) no-repeat 0 0; z-index: "+ac.chip+";");Y("=broken1","=abs","=thide","background: url("+af+"all.png) no-repeat 0 0; z-index: "+ac.chasm+";");Y("=broken2","=abs","=thide","=hide","z-index: "+ac.chasm+";");Y("=background","=abs","=thide","=hide","background: #050006 url("+af+"all.png) no-repeat 0 -462px; z-index: "+ac.background+";");Y("=forceground","=abs","=thide","=hide","height: 355px; background-color: #fff; z-index: "+ac.forceground+";");Y("=ticket-layer","=abs","=hide","z-index: "+ac.ticket+"; width: 602px; height: 258px; background: url("+af+"ticket.jpg) no-repeat 0 0;");Y("=ticket-shadow","=ticket-layer","background: #000;");Y("=loading","=abs","z-index: "+ac.text+"; background: url("+af+"all.png) no-repeat -829px -521px; width: 300px; height: 20px;");Y("=loading-bar","=hide","width: 0; height: 20px; background: url("+af+"all.png) no-repeat -829px -541px;");Y("=text","=abs","color: #a3cb4c; font-size: 18px; font-weight: 700; width: 72px; height: 20px; text-align: center; z-index: "+ac.text+";");Y("=join","=abs","width: 132px; height: 42px; background: url("+af+"all.png) no-repeat -687px -521px; z-index: "+ac.text+";");Y("=close","=abs","width: 27px; height: 27px; background: url("+af+"all.png) no-repeat -650px -521px; z-index: "+ac.text+";");var f=function(){
var ap=1;var e;var ao=function(){
};var an=r?function(aq,ar){
return{
left:aq+"px",top:ar+"px"}}:function(aq,ar){
return{
marginLeft:aq+"px",marginTop:ar+"px"}};return{
start:function(){
e=T(function(){
var ar=ap*2-D(ap*4);var aq=ap*1-D(ap*2);aa(U,an(ar,aq));ao(ar,aq)})},stop:function(){
clearInterval(e);t(function(){
aa(U,an(0,0));ao(0,0)},1)},setLevel:function(aq){
ap=aq},onQuake:function(aq){
ao=aq}}}();var h=function(){
var e=[[28,27,0],[22,27,27],[16,25,54]];return{
create:function(){
var aq=e[D(e.length)];var ao=-50,an=D(C.clientWidth)-40;var ap=n("div",C,["=chip",{
w:aq[0],h:aq[1],l:an,t:ao,"background-position":"-612px -"+(aq[2]+521)+"px"}]);a(function(ar){
ap.style.top=b(ar,ao,800,1)+"px"},1000).end(function(){
C.removeChild(ap)})}}}();var m;var P=function(){
var an=0;var at=af+"all.png";var ar=af+"broken.png";var e,az;var ao;var av=620;var aw=-7;var ax=68;var ay=227;var aq=452;var au=8;var ap=[];return{
prepare:function(){
var aB=ak(ay,q.clientWidth-av);e=n("div",C,["=broken1",{
l:av+"px",w:aB+"px",h:aq+"px"}],ap);az=n("div",C,["=broken2",{
l:av,w:aB,h:aq}],ap);az.innerHTML='<div style="width: '+(ay*au)+"px; height: "+aq+"px; "+(r?"filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ar+"',sizingMethod='scale');":"background: url("+ar+") right 0;")+'">&nbsp;</div>';ao=n("div",C,["=background",{
l:av+ax+15,w:B(C.clientWidth-av-ax-15,0),h:80}],ap);m=n("div",C,["=forceground",{
l:av+ax,w:B(C.clientWidth-av-ax,0)}],ap);var aA=n("div",m,[{
w:C.clientWidth,"z-index":ac.chasm,padding:"6px 0 0"}],ap);var aC=U.cloneNode(true);aC.style.position="relative";aA.appendChild(aC);this.disposeCloneEvent(aC);ap.push(aC);m.scrollLeft=100000;l(V,"resize",function(){
if(v.status){
return}ao.style.width=B(0,C.clientWidth-av-ax-15)+"px";m.style.width=B(0,C.clientWidth-av-ax)+"px";aA.style.width=C.clientWidth+"px";ao.style.background="#050006 url("+at+") no-repeat "+((C.clientWidth-av-ax-15)-1214)+"px -452px";m.scrollLeft=100000})},disposeCloneEvent:function(aA){
var aE=" u tools ";var aD=aA.getElementsByTagName("*");for(var aB=aD.length-1,aC,aF;aB>=0;aB--){
if(aC=aD[aB],aC.id&&(~aE.indexOf(" "+aC.id+" "))){
aF=document.getElementById(aC.id);am.push([aC,aF]);ag(aC,aF)}}},setLevel:function(aB){
an=aB;if(aB==0){
e.style.top=az.style.top=ao.style.top=m.style.top="-5000px";return}else{
e.style.top=az.style.top=aw+"px";ao.style.top="0";m.style.top="2px"}var aA=(aB-1)*ay;e.style.backgroundPosition="-"+aA+"px 0";az.scrollLeft=aA;if(aB>3){
ah(m,(aB-3)*0.8,"left bottom");ao.style.background="#050006 url("+at+") no-repeat "+((C.clientWidth-av-ax-15)-1214)+"px -452px"}else{
ah(m,0,"left bottom");ao.style.background="#fff"}},healing:function(){
var aB=s>d?d:s;var aC=ae(au-(aB/d)*au);var aD=au;var aA=T(function(){
if(aD==aC){
return clearInterval(aA)}aD>aC?(aD--):(aD++);P.setLevel(aD)},500)},fixPos:function(aB,aA){
if(an==0){
return}e.style.left=az.style.left=av+aB+"px";e.style.top=az.style.top=aA+aw+"px";m.style.left=av+ax+aB+"px";m.style.top=aA+2+"px"},dispose:function(){
for(var aA=ap.length-1;aA>=0;aA--){
try{
ap[aA].parentNode.removeChild(ap[aA])}catch(aB){
}}}}}();var k=function(){
var at=[642,265];var ap=602,ar=258,aJ=1.25;var av=[ap*(aJ-1)/2,ar*(aJ-1)/2];var aG=[[[543,56],[342,182],0.001],[[43,59],[172,192],1/9],[[852,150],[431,284],1/3]];var aE=[];for(var aH=1,az,aI,an,aF=aG.length;aH<aF;aH++){
az=aG[aH-1];aI=aG[aH];aE.push(u([az[0],az[1],aI[1],aI[0]],300))}var aw=aG[0],aA=aG[aG.length-1];var aB=aG[0][0],ay,aC,ax;var au=[640,480];var aD=[];var aK,aq,ao;return{
show:function(){
var aP=q.clientWidth,aS=q.clientHeight;ay=[(aP-ap)/2|0,(aS-ar)/2|0];aC=[aP/2|0,0];scDistance=640;aq=n("div",C,["=ticket-shadow"]);aK=n("div",C,["=ticket-layer"],aD);var aQ=[0,0],aR=aG[aG.length-1][2],aM=0;var aN=function(){
var aT=[0,0];return function(a4,a2,a0,a3,aU){
if(aT[0]==a4&&aT[1]==a2){
return}var aX=Math.sin(aU*=Math.PI/180)*a0,aV=Math.cos(aU)*a0;var a1=aX*0.5,aY=aX*0.75;if(O){
a4+=ap*a1,a2+=ar*aY}var aZ=[a4+ap*a0/2,a2+ar*a0/2];var aW=[(aZ[0]-aC[0])/scDistance,(aZ[1]-aC[1])/scDistance];aa(aK,{
left:a4+"px",top:a2+"px"});aa(aq,{
left:a4+au[0]*aW[0]*a0-av[0]*a0+"px",top:a2+au[1]*aW[1]*a0-av[1]*a0+"px"});ad(aq,a3);J(aK,aV,a1,aY,aV,"0 0");J(aq,aV*aJ,a1*aJ,aY*aJ,aV*aJ,"0 0");aT=[a4,a2]}}();var aL=function(){
var aT=I(360-aM)>aM?0:360;a(function(aU){
aN(p(aU,aQ[0],ay[0]-aQ[0],1),p(aU,aQ[1],ay[1]-aQ[1],1),p(aU,aR,1-aR,1),p(aU,0.33,-0.33,1),p(aU,aM,aT-aM,1))},1200,14).end(function(){
aq.parentNode.removeChild(aq);t(function(){
ao=true;k.turnOver(k.showLoadingBar)},1000)})};var e=function(aT,aV,aW,aU,aX){
a(function(aZ){
var a4=ae(p(aZ,0,299,1));var a3=p(aZ,aW,aU-aW,1);var aY=1-p(aZ,aV*0.33,0.33,1);var a1=aQ=aT[a4],a2;var a0;if(a2=aT[a4+1]){
aM=a0=(aj(g(a1,a2))+(aV%2?0:180))%360}aN(a1[0],a1[1],a3,aY,a0||aM)},1200,14).end(aX)};var aO=function(aT){
var aU=aE[aT];if(aU){
e(aU,aT,aG[aT][2],aG[aT+1][2],function(){
aO(aT+1)})}else{
aL()}};aO(0);l(V,"resize",function(){
if(v.status){
return}ay=[(q.clientWidth-ap)/2|0,(q.clientHeight-ar)/2|0];if(ao){
aa(aK,{
left:ay[0]+"px",top:ay[1]+"px"})}})},turnOver:function(aL){
var e=parseInt(aK.style.left);var aM=parseInt(aK.style.top);M(aK,ap,ar,e,aM,150).center(function(){
aK.style.backgroundPosition="0 -260px"}).end(aL)},showLoadingBar:function(){
var aU=[125,O?206:207];var aM=[77,230];var aN=[449,208];var aQ=[574,3];var aS="<span id='deliverer-num'></span>";var aO=n("div",C,["=loading",{
l:ay[0]+aU[0],t:ay[1]+aU[1]}],aD);var aR=n("div",aO,["=loading-bar"],aD);var aP=n("div",C,["=text",{
l:ay[0]+aM[0],t:ay[1]+aM[1]}],aD);var aV=n("div",C,["=join",{
l:ay[0]+aN[0],t:ay[1]+aN[1]}],aD);var e=n("div",C,["=close",{
l:ay[0]+aQ[0],t:ay[1]+aQ[1]}],aD);l(V,"resize",function(){
if(v.status){
return}ay=[(q.clientWidth-ap)/2|0,(q.clientHeight-ar)/2|0];Y(aO,{
l:ay[0]+aU[0],t:ay[1]+aU[1]});Y(aP,{
l:ay[0]+aM[0],t:ay[1]+aM[1]});Y(aV,{
l:ay[0]+aN[0],t:ay[1]+aN[1]});Y(e,{
l:ay[0]+aQ[0],t:ay[1]+aQ[1]})});aV.onmouseover=function(){
this.style.backgroundPosition="-687px -563px"};aV.onmouseout=function(){
this.style.backgroundPosition="-687px -521px"};aV.onclick=function(){
open(W)};e.onmouseover=function(){
this.style.backgroundPosition="-650px -548px"};e.onmouseout=function(){
this.style.backgroundPosition="-650px -521px"};e.onclick=function(){
v()};var aL=function(aW){
return ae(aW).toString().replace(/(\d)(?=(\d{
3})+$)/ig,"$1,")};aP.innerHTML=aS;var aT=i.getElementById("deliverer-num");a(function(aY){
var aX=L(aY,0,s,1);var aW=x(aY,0,s,1);aT.innerHTML=aL(aW);aR.style.width=ae(aX/d*20)*15+"px"},1200,24).end(function(){
t(function(){
P.healing()},1000)})},dispose:function(){
for(var aL=aD.length-1;aL>=0;aL--){
try{
aD[aL].parentNode.removeChild(aD[aL])}catch(aM){
}}}}}();var v=function(){
v.status=1;P.dispose();k.dispose();for(var e=am.length-1,an;e>=0;e--){
an=am[e];ag(an[0],an[1])}S()};try{
O&&i.execCommand("BackgroundImageCache",false,true)}catch(ai){
}var F=1;aa(U,r?({
position:"relative",left:0,top:0,display:"block"}):({
display:"block"}));f.onQuake(function(an,e){
if(D(10)<F){
h.create()}P.fixPos(an,e)});var z=document.createElement("script");z.type="text/javascript";z.src=j;C.appendChild(z);var Z=new Image();Z.src=af+"ticket.jpg";var c=new Image();c.onload=function(){
H();t(function(){
f.start();P.prepare()},0);t(function(){
P.setLevel(1)},500);for(var e=2;e<8;e++){
t(function(an){
return function(){
f.setLevel(F=an);P.setLevel(an)}}(e),e*400)}t(function(){
f.stop();P.setLevel(8)},3500);t(k.show,4500)};c.src=af+"all.png";function T(an,e){
return setInterval(an,e||ab)}function t(an,e){
return setTimeout(an,e||ab)}function D(e){
return Math.random()*e|0}function b(e,ap,ao,an){
return ao*(e/=an)*e+ap}function H(){
q.style.overflowX="hidden"}function S(){
q.style.overflowX="auto"}function aj(e){
return e*180/Math.PI}function g(an,e){
var ap=Math.PI;if(e[0]==an[0]){
if(e[1]>an[1]){
return ap*0.5}return ap*1.5}else{
if(e[1]==an[1]){
if(e[0]>an[0]){
return 0}return ap}}var ao=Math.atan((an[1]-e[1])/(an[0]-e[0]));if(e[0]>an[0]&&e[1]<an[1]){
return ao+2*ap}if(e[0]>an[0]&&e[1]>an[1]){
return ao}return ao+ap}function A(e){
return B((y(e[0],e[1])+y(e[1],e[2])+y(e[2],e[3]))|0,1)}function y(an,e){
return R(G(an[0]-e[0],2)+G(an[1]-e[1],2))}function Q(at,an){
var e=3*(at[1][0]-at[0][0]),ar=3*(at[2][0]-at[1][0])-e,ap=at[3][0]-at[0][0]-e-ar,au=3*(at[1][1]-at[0][1]),aq=3*(at[2][1]-at[1][1])-au,ao=at[3][1]-at[0][1]-au-aq;return[ap*G(an,3)+ar*G(an,2)+e*an+at[0][0],ao*G(an,3)+aq*G(an,2)+au*an+at[0][1]]}function u(an,ar){
if(ar<2){
return}var at=A(an);var aw=[];aw[0]={
p:an[0],t:0,l:0};for(var aq=1;aq<=at;aq++){
var ay=aq/at,e=Q(an,ay),ao=aw[aq-1].l+y(e,aw[aq-1].p);aw[aq]={
i:aq,p:e,t:ay,l:ao}}var au=aw[at].l;var az=[];var ay=0;for(var aq=0;aq<=ar;aq++){
var ax=au*(aq/(ar-1));for(var ap=ay;ap<aw.length;ap++){
var av=aw[ap];if(av.l>=ax){
ay=ap;az.push(av.p);break}}}return az}function L(e,ap,ao,an){
return ao*e/an+ap}function p(e,ap,ao,an){
if((e/=an/2)<1){
return ao/2*e*e*e+ap}else{
return ao/2*((e-=2)*e*e+2)+ap}}function x(e,ap,ao,an){
if(e==0){
return ap}else{
if(e==an){
return ap+ao}else{
if((e/=an/2)<1){
return ao/2*Math.pow(2,10*(e-1))+ap}else{
return ao/2*(-Math.pow(2,-10*--e)+2)+ap}}}}}(window);