
//��½
var jieqiUserId = 0;
var jieqiUserName = '';
var jieqiUserPassword = '';
var jieqiUserGroup = 0;
var jieqiNewMessage = 0;
var jieqiUserVip = 0;
var jieqiUserHonor = '';
var jieqiUserGroupName = '';
var jieqiUserVipName = '';
var timestamp = Math.ceil((new Date()).valueOf()/1000); //��ǰʱ���
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
��var returnvalue = ""; 
��if (document.cookie.length > 0) { 
��  offset = document.cookie.indexOf(search) 
����if (offset != -1) { 
����  offset += search.length 
����  end = document.cookie.indexOf(";", offset); 
����  if (end == -1) 
����  end = document.cookie.length; 
����  returnvalue=unescape(document.cookie.substring(offset, end));
����} 
��} 
��return returnvalue; 
}
//�жϵ�¼������ܻ��ߵ�¼��
function mybook(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln("<a id=\"idd\" class=\"favs\" href=\"javascript:void(0)\">�ҵ����</a>	");
}else{
document.writeln("<a id=\"mylogin\" class=\"favs\" href=\"javascript:void(0)\">�ҵ����</a>	");
}
}
//��Ϣҳ���
function mybookinfo(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln("<a id=\"idd\" class=\"btn-text btn-cate\" href=\"javascript:void(0)\">�鿴���</a>");
}else{
document.writeln("<a id=\"mylogin1\" class=\"btn-text btn-cate\" href=\"javascript:void(0)\">�鿴���</a>");
}
}
//������¼
function login(){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
	var jumpurl="";
  if(location.href.indexOf("jumpurl") == -1){
    jumpurl=location.href;
  }
  document.writeln('<ul><li><a style="color:#B01F2E; font-weight: bold;" href="/userdetail.php?uid='+jieqiUserId+'" target="_top">'+jieqiUserName+'</a> ��ã�</li><li>  <a href="/modules/article/bookcase.php?uid='+jieqiUserId+'" target="_top">�ҵ����</a></li> ');

  document.write('<li><em class="ver">|</em><a href="/logout.php?jumpurl='+jumpurl+'" target="_self">�˳���¼</a></li>');
  document.write('</ul>');
}else{
document.writeln("<form name=\"frmlogin\" id=\"frmlogin\" method=\"post\" action=\"/login.php?do=submit&action=login&usecookie=2592000&jumpurl=&jumpreferer=1\">");
document.writeln("<ul class=\"fm-ipt\">");
document.writeln("		<li>");
document.writeln("			<label for=\"phone\">��	��</label><input type=\"text\" name=\"username\" id=\"username\" size=\"15\" maxlength=\"30\" placeholder=\"����д�û���\" class=\"text\">");
document.writeln("		</li>");
document.writeln("		<li>");
document.writeln("			<label for=\"pass\">��	��</label><input type=\"password\" id=\"userpass\" name=\"password\" size=\"15\" maxlength=\"30\" placeholder=\"����������\" class=\"text\">");
document.writeln("		</li>");
document.writeln("		<span class=\"fl\">");
document.writeln("<input class=\"sub-btn\" type=\"submit\" value=\"��½\"><a href=\"/getpass.php\" title=\"��������\">��������</a> | <a href=\"/myreg.php\" title=\"�û�ע��\">�û�ע��</a></form>");
document.writeln("		</span>");
document.writeln("</ul>");
}
}

//�Ķ���¼
function jilu(){
	var CI = document.cookie.match(new RegExp("(^| )BLR=([^;]*)(;|$)"));
	if(CI!=null)
	{
		CI = unescape(CI[2]);
		LR = CI.split("#");
		if(LR[0]!=null && LR[1]!=null && LR[2]!=null && LR[3]!=null)
		{
			document.write('<li>����Ķ���<a href="/html/'+LR[0]+'/'+LR[1]+'.html" target="_blank" title=" '+LR[3]+'">'+LR[2]+'_'+LR[3]+'</a></li>');
		}
		else
		{
			document.write('<li style="color: #4fc25e">������û���Ķ����������ʵ������ݵ��������Ķ���</li>');
		}
	}
		else
	{
		document.write('<li style="color: #4fc25e">������û���Ķ����������ʵ������ݵ��������Ķ���</li>');
	}
}
//�Ƽ�
function tjl(aid){doAjax("/modules/article/uservote.php","id="+aid,"tjl2","GET",0)}
function tjl2(t){
	alert('�ϵ۰���������һ����˿�����ܲ����ˡ�����');
	}
	//���
function shujia(aa){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln('<a href="javascript:void(0)" class="btn-text btn-collect" style="display: inline-block;" onclick="shujia1('+aa+')">�������</a>');
}else{
document.writeln('<a id=\"mylogin\" class="btn-text btn-collect" style="display: inline-block;" href=\"javascript:void(0)\">�������</a>');
}
}
function shujia1(aid){doAjax("/modules/article/addbookcase.php","bid="+aid,"shujia2","GET",0)}
function shujia2(t){
	alert('�Ѽ�����ܣ�');
	}	

//��ǩ
function shuqian(bid,cid){
if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
document.writeln('<a href="javascript:void(0)" onclick="shuqian1('+bid+','+cid+')">������ǩ</a>');
}else{
document.writeln('<a id="mylogin1"  href="javascript:void(0)">������ǩ</a>	');
}
}
function shuqian1(bid,cid){doAjax("/modules/article/addbookcase.php","bid="+bid+"&cid="+cid,"shuqian2","GET",0)}
function shuqian2(t){
	alert('�Ѽ�����ǩ���´ο��������ֱ�Ӳ鿴��');
	exit;
	}

//��ӵ��ղؼ�
function AddFavorite(url,title) {
	if(confirm("�ղ����ƣ�"+title+"\n�ղ���ַ��"+url+"\nȷ������ղأ�")) {
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
					alert("�����ղ�ʧ�ܣ���ʹ��Ctrl+D�������");
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