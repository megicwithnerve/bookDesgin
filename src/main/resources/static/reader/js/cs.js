 layer.config({
                    skin:'layer-ext-espresso',
                    extend:'skin/espresso/style.css'
                });
                

				
$('#history').on('click', function(){
    layer.open({
        type: 2,
        title: '��ʱ���',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['820px' , '520px'],
        content: '/history.html'
		
    });
});
$('#history1').on('click', function(){
    layer.open({
        type: 2,
        title: '��ʱ���',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['820px' , '520px'],
        content: '/history.html'
		
    });
});

$('#idd').on('click', function(){
    layer.open({
        type: 2,
        title: '�ҵ����',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['820px' , '520px'],
        content: '/modules/article/mybook.php'
		
    });
});
$('#mylogin').on('click', function(){
    layer.open({
        type: 1,
        title: '���¼',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['380px' , '320px'],
        content: '<form name="frmlogin"method="post"action="/mylogin.php?jumpurl=&jumpreferer=1"><ul class="layui-form"><li class="layui-form-li"><label for="username">�û�����</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onKeyPress="javascript: if (event.keyCode==32) return false;"/></li><li class="layui-form-li"><label for="password">���룺</label><input size="25"maxlength="20"type="password"id="password"name="password"/></li><li class="layui-form-li"><label for="repassword">��Ч�ڣ�</label><span class="layui-from-msg"><select name="usecookie"class="select"><option value="0">���������</option><option value="86400">����һ��</option><option value="2592000">����һ��</option><option value="315360000">����һ��</option></select></span></li><li class="layui-form-block"><input type="hidden"name="action"value="login"><input type="submit"class="layui-btn"name="submit"id="submit"value="�� ��"class="layui-btn"></li><li style="margin-bottom:5px"class="layui-form-li"><span><a style="margin-left:25px" href="/myreg.php">what?��û�ʺţ��Ͻ�ע��!</a><a style="margin-left:25px;color:red"href="/getpass.php">�������ǲ�����!</a></span></li></ul></form>'
    });
});
$('#mylogin1').on('click', function(){
    layer.open({
        type: 1,
        title: '���¼',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['380px' , '320px'],
        content: '<form name="frmlogin"method="post"action="/mylogin.php?jumpurl=&jumpreferer=1"><ul class="layui-form"><li class="layui-form-li"><label for="username">�û�����</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onKeyPress="javascript: if (event.keyCode==32) return false;"/></li><li class="layui-form-li"><label for="password">���룺</label><input size="25"maxlength="20"type="password"id="password"name="password"/></li><li class="layui-form-li"><label for="repassword">��Ч�ڣ�</label><span class="layui-from-msg"><select name="usecookie"class="select"><option value="0">���������</option><option value="86400">����һ��</option><option value="2592000">����һ��</option><option value="315360000">����һ��</option></select></span></li><li class="layui-form-block"><input type="hidden"name="action"value="login"><input type="submit"class="layui-btn"name="submit"id="submit"value="�� ��"class="layui-btn"></li><li style="margin-bottom:5px"class="layui-form-li"><span><a style="margin-left:25px" href="/myreg.php">what?��û�ʺţ��Ͻ�ע��!</a><a style="margin-left:25px;color:red"href="/getpass.php">�������ǲ�����!</a></span></li></ul></form>'
    });
});
$('#myreg').on('click', function(){
    layer.open({
        type: 1,
        title: '�û�ע��',
        //maxmin: true,
        shadeClose: true, //������ֹرղ�
        area : ['520px' , '350px'],
        content: '<form name="frmregister"id="frmregister"action="/register.php?do=submit"method="post"><ul class="layui-form"><li class="layui-form-li"><label for="username">�û���*��</label><input size="25"maxlength="30"type="text"id="username"name="username"autocomplete="off"onBlur="Ajax.Update(\'/regcheck.php?item=u&username=\'+this.value, {outid:\'usermsg\', tipid:\'usermsg\', onLoading:\'Loading...\'});"/><span id="usermsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="password">����*��</label><input size="25"maxlength="20"type="password"id="password"name="password"onBlur="Ajax.Update(\'/regcheck.php?item=p&password=\'+this.value, {outid:\'passmsg\', tipid:\'passmsg\', onLoading:\'Loading...\'});"/><span id="passmsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="repassword">ȷ������*��</label><input size="25"maxlength="20"type="password"id="repassword"name="repassword"onBlur="Ajax.Update(\'/regcheck.php?item=r&password=\'+password.value+\'&repassword=\'+this.value, {outid:\'repassmsg\', tipid:\'repassmsg\', onLoading:\' Loading...\'});"/><span id="repassmsg"class="layui-from-msg"></span></li><li class="layui-form-li"><label for="email">����*��</label><input size="25"maxlength="20"type="text"id="email"name="email"onBlur="Ajax.Update(\'/regcheck.php?item=m&email=\'+this.value, {outid:\'mailmsg\', tipid:\'mailmsg\', onLoading:\' Loading...\'});"/><span id="mailmsg"class="layui-from-msg"></span></li><li class="layui-form-block"><input type="hidden"name="action"id="action"value="newuser"/><input type="submit"class="layui-btn"name="submit"id="submit"value="�� ��"class="layui-btn"><span><a href="/mylogin.php">�����ʺŵ����¼</a></span></li></ul></form>'
    });
});