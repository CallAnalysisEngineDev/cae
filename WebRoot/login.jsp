<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="http://localhost/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="http://localhost/browser-storage.js"></script>
    <script type="text/javascript" src="http://localhost/DES3.js"></script>
    <script src="http://localhost/jsencrypt.min.js"></script>
	<script type="text/javascript">
		var key;
		var KEY_NAME = "3des_key";
		var PUBLIC_KEY_NAME = "public_key";
		var encrypt = new JSEncrypt();
		function login_1(){
			BrowserStorage.api.clearAll();
			generateKey();
			ask_for_publickey();
		}
		function login_2(data){
			if(data.publicKey!=null){
				BrowserStorage.api.set({
					"key" : PUBLIC_KEY_NAME,
					"value" : data.publicKey,
					"expires" : 86400
				});
				console.log("收到服务器的公钥:"+data.publicKey);
		        encrypt.setPublicKey(data.publicKey);
			}
			var encryptKey=encrypt.encrypt(key.value);
			var encryptAdminUserAccount=DES3.encrypt(key.value,$("#useraccount").val());
			var encryptAdminUserPassword=DES3.encrypt(key.value,$("#password").val());
			var formData={
				"u":encryptAdminUserAccount,
				"p":encryptAdminUserPassword,
				"k":encryptKey
			};
			$("#message").val(JSON.stringify(formData));
			$.ajax({
				url:"admin/shakeHand",
				type:"post",
				data:{
					"type":2,
					"message":JSON.stringify(formData)
				},
				success:login_3,
				fail:fail
			});
		}
		function login_3(data){
			var result=data.successed;
			if(result){
				$("#admin_login_form").submit();
			}
			else{
				alert(data.errInfo);
			}
		}
		function generateKey(){
			key=BrowserStorage.api.get(KEY_NAME);
			if(key.value!=null){
				return;
			}
			BrowserStorage.api.set({
				"key" : KEY_NAME,
				"value" : randomString(116),
				"expires" : 86400
			});
			key=BrowserStorage.api.get(KEY_NAME);
			console.log("当前的对称秘钥是:"+key.value);
		}
		function randomString(len) {
			len = len || 32;
			var $chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			var maxPos = $chars.length;
			var pwd = "";
			for (var i = 0; i < len; i++) {
				pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
			}
			return pwd;
		}
		function ask_for_publickey(){
			$.ajax({
				url:"admin/shakeHand",
				type:"get",
				data:{
					"type":1
				},
				success:login_2,
				fail:fail
			});
		}
		function fail(errInfo){
			console.log("握手失败,失败原因:"+errInfo);
		}
	</script>
  </head>
  
  <body>
  	<center>
  		<h1>管理员登录</h1>
    	账号:<input type="text" id="useraccount" /><br/>
    	密码:<input type="password" id="password" /><br/>
    	<input type="button" onclick="login_1()" value="登录"/>
  	</center>
  	<form action="admin/all" method="post" id="admin_login_form">
  		<input type="hidden" name="type" value="2" />
    	<input type="hidden" name="message" id="message" />
  	</form>
  </body>
</html>
