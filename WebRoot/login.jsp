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
	<script type="text/javascript" src="http://localhost/tripledes2.js"></script>  
    <script type="text/javascript" src="http://localhost/cipher-core-min.js"></script>    
    <script type="text/javascript" src="http://localhost/core-min.js"></script>  
    <script type="text/javascript" src="http://localhost/mode-ecb-min.js"></script>
    <script type="text/javascript" src="http://localhost/browser-storage.js"></script>
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
			console.log("加密后的对称秘钥:"+encryptKey);
			var encryptAdminUserAccount=encryptByDES($("#useraccount").val());
			console.log("加密后的账号:"+encryptAdminUserAccount);
			var encryptAdminUserPassword=encryptByDES($("#password").val());
			console.log("加密后的密码:"+encryptAdminUserPassword);
			var formData={
				"useraccount":encryptAdminUserAccount,
				"password":encryptAdminUserPassword,
				"key":encryptKey
			};
			$("#message").val(JSON.stringify(formData));
			$("#admin_login_form").submit();
		}
		function generateKey(){
			key=BrowserStorage.api.get(KEY_NAME);
			if(key.value!=null){
				return;
			}
			BrowserStorage.api.set({
				"key" : KEY_NAME,
				"value" : randomString(32),
				"expires" : 86400
			});
			key=BrowserStorage.api.get(KEY_NAME);
			console.log("当前的对称秘钥是:"+key.value);
		}
		function randomString(len) {
			len = len || 32;
			var $chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
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
		function encryptByDES(message) {
	        var keyHex = CryptoJS.enc.Utf8.parse(key.value);  
	        var encrypted = CryptoJS.DES.encrypt(message, keyHex, {    
		        mode: CryptoJS.mode.ECB,    
		        padding: CryptoJS.pad.Pkcs7    
	        });   
	        return encrypted.toString();    
	    }    
	    function decryptByDES(ciphertext) {    
	        var keyHex = CryptoJS.enc.Utf8.parse(key.value);
	        var decrypted = CryptoJS.DES.decrypt({    
	                ciphertext: CryptoJS.enc.Base64.parse(ciphertext)    
	        }, keyHex, {    
	            mode: CryptoJS.mode.ECB,    
	            padding: CryptoJS.pad.Pkcs7    
	        });  
	        return decrypted.toString(CryptoJS.enc.Utf8);    
	    }
	</script>
  </head>
  
  <body>
  	<textarea hidden id="privkey" rows="15" cols="65">-----BEGIN RSA PRIVATE KEY-----
MIICXQIBAAKBgQDlOJu6TyygqxfWT7eLtGDwajtNFOb9I5XRb6khyfD1Yt3YiCgQ
WMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76xFxdU6jE0NQ+Z+zEdhUTooNR
aY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4gwQco1KRMDSmXSMkDwIDAQAB
AoGAfY9LpnuWK5Bs50UVep5c93SJdUi82u7yMx4iHFMc/Z2hfenfYEzu+57fI4fv
xTQ//5DbzRR/XKb8ulNv6+CHyPF31xk7YOBfkGI8qjLoq06V+FyBfDSwL8KbLyeH
m7KUZnLNQbk8yGLzB3iYKkRHlmUanQGaNMIJziWOkN+N9dECQQD0ONYRNZeuM8zd
8XJTSdcIX4a3gy3GGCJxOzv16XHxD03GW6UNLmfPwenKu+cdrQeaqEixrCejXdAF
z/7+BSMpAkEA8EaSOeP5Xr3ZrbiKzi6TGMwHMvC7HdJxaBJbVRfApFrE0/mPwmP5
rN7QwjrMY+0+AbXcm8mRQyQ1+IGEembsdwJBAN6az8Rv7QnD/YBvi52POIlRSSIM
V7SwWvSK4WSMnGb1ZBbhgdg57DXaspcwHsFV7hByQ5BvMtIduHcT14ECfcECQATe
aTgjFnqE/lQ22Rk0eGaYO80cc643BXVGafNfd9fcvwBMnk0iGX0XRsOozVt5Azil
psLBYuApa66NcVHJpCECQQDTjI2AQhFc1yRnCU/YgDnSpJVm1nASoRUnU8Jfm3Oz
uku7JUXcVpt08DFSceCEX9unCuMcT72rAQlLpdZir876
-----END RSA PRIVATE KEY-----</textarea>
  	<center>
  		<h1>管理员登录</h1>
  		<form action="admin/shakeHand" method="post" id="admin_login_form">
    		账号:<input type="text" id="useraccount" /><br/>
    		密码:<input type="password" id="password" /><br/>
    		<input type="hidden" name="type" value="2" />
    		<input type="hidden" name="message" id="message" />
    		<input type="button" onclick="login_1()" value="登录"/>
    	</form>
  	</center>
  </body>
</html>
