var Common = (function () {
	function Common(){}
	
    Common.randomString = function (len) {
    	len = len || 32;
        var $chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var maxPos = $chars.length;
        var pwd = "";
        for (var i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    };
    
    Common.ajax = function (url, type, data, success) {
        $.ajax({
        	url:url,
        	type:type,
        	data:data,
        	success:success,
        	error:function(errInfo){this.error(errInfo);}
        });
    };
    
    function error(errInfo){
    	console.error("握手失败,失败原因:"+errInfo);
    }
    
    return Common;
}());
Common.key;
Common.KEY_NAME = "3des_key";
Common.PUBLIC_KEY_NAME = "public_key";
Common.ASK_PUBKEY = 1;
Common.ENCTYPT_DATA = 2;

var Login = (function(){
	var encrypt = null;
	function Login(){
		encrypt = new Encrypt();
	}
	
	Login.prototype.login_1 = function(){
		BrowserStorage.api.clearAll();
		generateKey();
		askForPublickey();
	};
	
	function generateKey(){
		Common.key=BrowserStorage.api.get(Common.KEY_NAME);
		if(Common.key.value!=null){
			return;
		}
		BrowserStorage.api.set({
			"key" : Common.KEY_NAME,
			"value" : Common.randomString(116),
			"expires" : 86400
		});
		Common.key=BrowserStorage.api.get(Common.KEY_NAME);
	};
	
	function askForPublickey(){
		var data={
			"type":Common.ASK_PUBKEY
		};
		Common.ajax("admin/shakeHand", "get", data, login_2);
	}
	
	function login_2(data){
		if(data.publicKey!=null){
			BrowserStorage.api.set({
				"key" : Common.PUBLIC_KEY_NAME,
				"value" : data.publicKey,
				"expires" : 86400
			});
	        encrypt.setPublicKey(data.publicKey);
		}
		var encryptKey=encrypt.encrypt_RSA(Common.key.value);
		var encryptAdminUserAccount=encrypt.encrypt_3DES($("#useraccount").val());
		var encryptAdminUserPassword=encrypt.encrypt_3DES($("#password").val());
		var formData={
			"u":encryptAdminUserAccount,
			"p":encryptAdminUserPassword,
			"k":encryptKey
		};
		$("#message").val(JSON.stringify(formData));
		var data={
			"type":Common.ENCTYPT_DATA,
			"message":JSON.stringify(formData)
		};
		Common.ajax("admin/shakeHand", "post", data, login_3);
	};
	
	function login_3(data){
		var result=data.successed;
		if(result){
			$("#admin_login_form").submit();
		}
		else{
			alert(data.errInfo);
		}
	};
	
	return Login;
}());

var Encrypt = (function(){
	function Encrypt(){};
	
	var encrypt = new JSEncrypt();
	
	Encrypt.prototype.setPublicKey = function(publicKey){
		encrypt.setPublicKey(publicKey);
	};
	
	Encrypt.prototype.encrypt_RSA = function(text){
		return encrypt.encrypt(text);
	};
	
	Encrypt.prototype.encrypt_3DES = function(text){
		return DES3.encrypt(Common.key.value,text);
	};
	
	return Encrypt;
}());