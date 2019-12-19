
//登陆
var jieqiUserId = 0;
var jieqiUserName = '';
var jieqiUserPassword = '';
var jieqiUserGroup = 0;
var jieqiNewMessage = 0;
var jieqiUserVip = 0;
var jieqiUserHonor = '';
var jieqiUserGroupName = '';
var jieqiUserVipName = '';
var timestamp = Math.ceil((new Date()).valueOf()/1000); //当前时间戳
var flag_overtime = -1;
if(document.cookie.indexOf('jieqiUserInfo') >= 0){
	var jieqiUserInfo = get_cookie_value('jieqiUserInfo');
	//document.write(jieqiUserInfo);
	start = 0;
	offset = jieqiUserInfo.indexOf(',', start); 
	while(offset > 0){
		tmpval = jieqiUserInfo.substring(start, offset);
		tmpidx = tmpval.indexOf('=');
		if(tmpidx > 0){
           tmpname = tmpval.substring(0, tmpidx);
		   tmpval = tmpval.substring(tmpidx+1, tmpval.length);
		   if(tmpname == 'jieqiUserId') jieqiUserId = tmpval;
		   else if(tmpname == 'jieqiUserName_un') jieqiUserName = tmpval;
		   else if(tmpname == 'jieqiUserPassword') jieqiUserPassword = tmpval;
		   else if(tmpname == 'jieqiUserGroup') jieqiUserGroup = tmpval;
		   else if(tmpname == 'jieqiNewMessage') jieqiNewMessage = tmpval;
		   else if(tmpname == 'jieqiUserVip') jieqiUserVip = tmpval;
		   else if(tmpname == 'jieqiUserHonor_un') jieqiUserHonor = tmpval;
		   else if(tmpname == 'jieqiUserGroupName_un') jieqiUserGroupName = tmpval;
		}
		start = offset+1;
		if(offset < jieqiUserInfo.length){
		  offset = jieqiUserInfo.indexOf(',', start); 
		  if(offset == -1) offset =  jieqiUserInfo.length;
		}else{
          offset = -1;
		}
	}
	flag_overtime = get_cookie_value('overtime');
} else {
	delCookie('overtime');
}
function delCookie(name){
   var date = new Date();
   date.setTime(date.getTime() - 10000);
   document.cookie = name + "=a; expires=" + date.toGMTString();
}

function get_cookie_value(Name) { 
  var search = Name + "=";
　var returnvalue = ""; 
　if (document.cookie.length > 0) { 
　  offset = document.cookie.indexOf(search) 
　　if (offset != -1) { 
　　  offset += search.length 
　　  end = document.cookie.indexOf(";", offset); 
　　  if (end == -1) 
　　  end = document.cookie.length; 
　　  returnvalue=unescape(document.cookie.substring(offset, end));
　　} 
　} 
　return returnvalue; 
}
//判断登录弹出书架或者登录框
function mybook(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln("<a id=\"idd\" class=\"favs\" href=\"javascript:void(0)\">我的书架</a>	");
}else{
document.writeln("<a id=\"mylogin\" class=\"favs\" href=\"javascript:void(0)\">我的书架</a>	");
}
}
//信息页书架
function mybookinfo(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln("<a id=\"idd\" class=\"btn-text btn-cate\" href=\"javascript:void(0)\">查看书架</a>");
}else{
document.writeln("<a id=\"mylogin1\" class=\"btn-text btn-cate\" href=\"javascript:void(0)\">查看书架</a>");
}
}
//顶部登录
function login(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
	var jumpurl="";
  if(location.href.indexOf("jumpurl") == -1){
    jumpurl=location.href;
  }
  document.writeln('<ul><li><a style="color:#B01F2E; font-weight: bold;" href="/userdetail.php?uid='+jieqiUserId+'" target="_top">'+jieqiUserName+'</a> 你好！</li><li>  <a href="/modules/article/bookcase.php?uid='+jieqiUserId+'" target="_top">我的书架</a></li> ');

  document.write('<li><em class="ver">|</em><a href="/logout.php?jumpurl='+jumpurl+'" target="_self">退出登录</a></li>');
  document.write('</ul>');
}else{
document.writeln("<form name=\"frmlogin\" id=\"frmlogin\" method=\"post\" action=\"/login.php?do=submit&action=login&usecookie=2592000&jumpurl=&jumpreferer=1\">");
document.writeln("<ul class=\"fm-ipt\">");
document.writeln("		<li>");
document.writeln("			<label for=\"phone\">帐	号</label><input type=\"text\" name=\"username\" id=\"username\" size=\"15\" maxlength=\"30\" placeholder=\"请填写用户名\" class=\"text\">");
document.writeln("		</li>");
document.writeln("		<li>");
document.writeln("			<label for=\"pass\">密	码</label><input type=\"password\" id=\"userpass\" name=\"password\" size=\"15\" maxlength=\"30\" placeholder=\"请输入密码\" class=\"text\">");
document.writeln("		</li>");
document.writeln("		<span class=\"fl\">");
document.writeln("<input class=\"sub-btn\" type=\"submit\" value=\"登陆\"><a href=\"/getpass.php\" title=\"忘记密码\">忘记密码</a> | <a href=\"/myreg.php\" title=\"用户注册\">用户注册</a></form>");
document.writeln("		</span>");
document.writeln("</ul>");
}
}

//阅读记录
function jilu(){
	var CI = document.cookie.match(new RegExp("(^| )BLR=([^;]*)(;|$)"));
	if(CI!=null)
	{
		CI = unescape(CI[2]);
		LR = CI.split("#");
		if(LR[0]!=null && LR[1]!=null && LR[2]!=null && LR[3]!=null)
		{
			document.write('<li>最近阅读：<a href="/html/'+LR[0]+'/'+LR[1]+'.html" target="_blank" title=" '+LR[3]+'">'+LR[2]+'_'+LR[3]+'</a></li>');
		}
		else
		{
			document.write('<li style="color: #4fc25e">您近期没有阅读，超级精彩的新内容等着您来阅读。</li>');
		}
	}
		else
	{
		document.write('<li style="color: #4fc25e">您近期没有阅读，超级精彩的新内容等着您来阅读。</li>');
	}
}
//推荐
function tjl(aid){doAjax("/modules/article/uservote.php","id="+aid,"tjl2","GET",0)}
function tjl2(t){
	alert('上帝啊！又来了一个粉丝，我受不了了……！');
	}
	//书架
function shujia(aa){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln('<a href="javascript:void(0)" class="btn-text btn-collect" style="display: inline-block;" onclick="shujia1('+aa+')">加入书架</a>');
}else{
document.writeln('<a id=\"mylogin\" class="btn-text btn-collect" style="display: inline-block;" href=\"javascript:void(0)\">加入书架</a>');
}
}
function shujia1(aid){doAjax("/modules/article/addbookcase.php","bid="+aid,"shujia2","GET",0)}
function shujia2(t){
	alert('已加入书架！');
	}	

//书签
function shuqian(bid,cid){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln('<a href="javascript:void(0)" onclick="shuqian1('+bid+','+cid+')">加入书签</a>');
}else{
document.writeln('<a id="mylogin1"  href="javascript:void(0)">加入书签</a>	');
}
}
function shuqian1(bid,cid){doAjax("/modules/article/addbookcase.php","bid="+bid+"&cid="+cid,"shuqian2","GET",0)}
function shuqian2(t){
	alert('已加入书签，下次可在书架中直接查看！');
	exit;
	}

//添加到收藏夹
function AddFavorite(url,title) {
	if(confirm("收藏名称："+title+"\n收藏网址："+url+"\n确定添加收藏？")) {
		var ua = navigator.userAgent.toLowerCase();
			if(ua.indexOf("msie 8")>-1) {
			external.AddToFavoritesBar(url, title);//IE8
		}else{
			try {
				window.external.addFavorite(url, title);
			} catch(e) {
				try {
					window.sidebar.addPanel(title, url, "");//firefox
				} catch(e) {
					alert("加入收藏失败，请使用Ctrl+D进行添加");
				}
			}
		}
	}
	return false;
}
//doAjax
function getXMLHttpRequest(){
		try{
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");	
			}
			catch(e){
				return new ActiveXObject("Msxml2.XMLHTTP");
			}
		}
		catch(e){
			return new XMLHttpRequest();
		}
	
	}

function doAjax(url,query,callback,reqtype,getxml){
	var myreq = getXMLHttpRequest();
	myreq.onreadystatechange = function(){
		if(myreq.readyState == 4){
			if(myreq.status == 200){
					var item = myreq.responseText;
					if(getxml == 1){
						item =myreq.responseXML;	
					}
					eval(callback + '(item)');
				}	
		}
		else{
			//backing();
		}	
	}
	if(reqtype.toUpperCase()== "POST"){
		requestPOST(url,query,myreq);
	}
	else{
		requestGET(url,query,myreq);	
	}
}

function requestGET(url,query,req){
	var myRandom = parseInt(Math.random()*99999999);
	
	if(query == ''){
		var callUrl = url + '?rand' + myRandom;	
	}
	else{
		var callUrl = url + '?' + query + '&rand=' + myRandom;	
	}
	req.open("GET",callUrl,true);
	req.send(null);
}

function requestPOST(url,query,req){
	req.open("POST",url,true);
	//req.setRequestHeader('Content-Type','applicate/x-www-form-urlencoded');
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	req.send(query);	
}
function uaredirect(f){try{if(document.getElementById("bdmark")!=null){return}var b=false;if(arguments[1]){var e=window.location.host;var a=window.location.href;if(isSubdomain(arguments[1],e)==1){f=f+"/#m/"+a;b=true}else{if(isSubdomain(arguments[1],e)==2){f=f+"/#m/"+a;b=true}else{f=a;b=false}}}else{b=true}if(b){var c=window.location.hash;if(!c.match("fromapp")){if((navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i))){location.replace(f)}}}}catch(d){}}function isSubdomain(c,d){this.getdomain=function(f){var e=f.indexOf("://");if(e>0){var h=f.substr(e+3)}else{var h=f}var g=/^www\./;if(g.test(h)){h=h.substr(4)}return h};if(c==d){return 1}else{var c=this.getdomain(c);var b=this.getdomain(d);if(c==b){return 1}else{c=c.replace(".","\\.");var a=new RegExp("\\."+c+"$");if(b.match(a)){return 2}else{return 0}}}};