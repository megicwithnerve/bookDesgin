 layer.config({
                    skin:'layer-ext-espresso',
                    extend:'skin/espresso/style.css'
                });
                

				
$('#history').on('click', function(){
    layer.open({
        type: 2,
        title: '临时书架',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['820px' , '520px'],
        content: '/history.html'
		
    });
});
$('#history1').on('click', function(){
    layer.open({
        type: 2,
        title: '临时书架',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['820px' , '520px'],
        content: '/history.html'
		
    });
});

$('#idd').on('click', function(){
    layer.open({
        type: 2,
        title: '我的书架',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['820px' , '520px'],
        content: '/modules/article/mybook.php'
		
    });
});
$('#mylogin').on('click', function(){
    layer.open({
        type: 1,
        title: '请登录',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['380px' , '320px'],
        content: '<form name="frmlogin"method="post"action="/mylogin.php?jumpurl=&jumpreferer=1"><ul class="layui-form"><li class="layui-form-li"><label for="username">用户名：</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onKeyPress="javascript: if (event.keyCode==32) return false;"/></li><li class="layui-form-li"><label for="password">密码：</label><input size="25"maxlength="20"type="password"id="password"name="password"/></li><li class="layui-form-li"><label for="repassword">有效期：</label><span class="layui-from-msg"><select name="usecookie"class="select"><option value="0">浏览器进程</option><option value="86400">保存一天</option><option value="2592000">保存一月</option><option value="315360000">保存一年</option></select></span></li><li class="layui-form-block"><input type="hidden"name="action"value="login"><input type="submit"class="layui-btn"name="submit"id="submit"value="提 交"class="layui-btn"></li><li style="margin-bottom:5px"class="layui-form-li"><span><a style="margin-left:25px" href="/myreg.php">what?还没帐号，赶紧注册!</a><a style="margin-left:25px;color:red"href="/getpass.php">密码好像记不得了!</a></span></li></ul></form>'
    });
});
$('#mylogin1').on('click', function(){
    layer.open({
        type: 1,
        title: '请登录',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['380px' , '320px'],
        content: '<form name="frmlogin"method="post"action="/mylogin.php?jumpurl=&jumpreferer=1"><ul class="layui-form"><li class="layui-form-li"><label for="username">用户名：</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onKeyPress="javascript: if (event.keyCode==32) return false;"/></li><li class="layui-form-li"><label for="password">密码：</label><input size="25"maxlength="20"type="password"id="password"name="password"/></li><li class="layui-form-li"><label for="repassword">有效期：</label><span class="layui-from-msg"><select name="usecookie"class="select"><option value="0">浏览器进程</option><option value="86400">保存一天</option><option value="2592000">保存一月</option><option value="315360000">保存一年</option></select></span></li><li class="layui-form-block"><input type="hidden"name="action"value="login"><input type="submit"class="layui-btn"name="submit"id="submit"value="提 交"class="layui-btn"></li><li style="margin-bottom:5px"class="layui-form-li"><span><a style="margin-left:25px" href="/myreg.php">what?还没帐号，赶紧注册!</a><a style="margin-left:25px;color:red"href="/getpass.php">密码好像记不得了!</a></span></li></ul></form>'
    });
});
$('#myreg').on('click', function(){
    layer.open({
        type: 1,
        title: '用户注册',
        //maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['520px' , '350px'],
        content: '<form name="frmregister"id="frmregister"action="/register.php?do=submit"method="post"><ul class="layui-form"><li class="layui-form-li"><label for="username">用户名*：</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onBlur="Ajax.Update(\'/regcheck.php?item=u&username=\'+this.value, {outid:\'usermsg\', tipid:\'usermsg\', onLoading:\'Loading...\'});"/><span id="usermsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="password">密码*：</label><input size="25"maxlength="20"type="password"id="password"name="password"onBlur="Ajax.Update(\'/regcheck.php?item=p&password=\'+this.value, {outid:\'passmsg\', tipid:\'passmsg\', onLoading:\'Loading...\'});"/><span id="passmsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="repassword">确认密码*：</label><input size="25"maxlength="20"type="password"id="repassword"name="repassword"onBlur="Ajax.Update(\'/regcheck.php?item=r&password=\'+password.value+\'&repassword=\'+this.value, {outid:\'repassmsg\', tipid:\'repassmsg\', onLoading:\' Loading...\'});"/><span id="repassmsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="email">邮箱*：</label><input size="25"maxlength="20"type="text"id="email"name="email"onBlur="Ajax.Update(\'/regcheck.php?item=m&email=\'+this.value, {outid:\'mailmsg\', tipid:\'mailmsg\', onLoading:\' Loading...\'});"/><span id="mailmsg"class="layui-from-msg"></span></li><li class="layui-form-block"><input type="hidden"name="action"id="action"value="newuser"/><input type="submit"class="layui-btn"name="submit"id="submit"value="提 交"class="layui-btn"><span><a href="/mylogin.php">已有帐号点击登录</a></span></li></ul></form>'
    });
});