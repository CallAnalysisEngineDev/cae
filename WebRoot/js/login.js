/**
 * 加密的公共类Common,里面封装了一些加密握手时会用到的公共方法
 */
var Common = (function () {
	function Common(){}
	
	//获取一个随机字符串,长度为len,一般用于客户端生成对称秘钥
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
    
    //jquery的ajax请求封装
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
//以下是一些静态全局变量
//对称秘钥
Common.key;
//使用本地缓存保存对称秘钥的key
Common.KEY_NAME = "3des_key";
//使用本地缓存保存公钥的key
Common.PUBLIC_KEY_NAME = "public_key";
//对应服务器端org.cae.security.SecurityAlgorithm.ASK_PUBKEY状态,具体含义参考设计文档
Common.ASK_PUBKEY = 1;
//对应服务器端org.cae.security.SecurityAlgorithm.ENCTYPT_DATA状态,具体含义参考设计文档
Common.ENCTYPT_DATA = 2;
//对称秘钥的长度,越长越安全,但性能越低,目前最高只能到116
Common.KEY_SIZE = 116;

/**
 * 登陆过程中的握手函数类
 */
var Login = (function(){
	//加密算法对象成员
	var encrypt = null;
	
	function Login(){
		encrypt = new Encrypt();
	}
	
	//客户端登录握手第一步
	Login.prototype.login_1 =function(){
		//先生成对称秘钥
		generateKey();
		//再问服务器拿公钥
		askForPublickey();
	};
	
	//生成对称秘钥的函数
	function generateKey(){
		//先判断本地缓存中是否已经保存了对称秘钥
		Common.key=BrowserStorage.api.get(Common.KEY_NAME);
		//如果已经有,证明之前已经生成过对称秘钥,所以不再生成新的对称秘钥,直接返回
		if(Common.key.value!=null){
			return;
		}
		//生成对称密钥并放入本地缓存
		BrowserStorage.api.set({
			"key" : Common.KEY_NAME,
			"value" : Common.randomString(116),//生成随机字符串作为对称密钥
			"expires" : 86400//设置本地缓存过期时间,目前为1天
		});
		Common.key=BrowserStorage.api.get(Common.KEY_NAME);
	};
	
	//询问服务器公钥的方法
	function askForPublickey(){
		var data={
			"type":Common.ASK_PUBKEY//data中指明这个type就代表该请求是用来获取服务器的公钥的
		};
		//如果获取成功,则进入login_2函数
		Common.ajax("admin/shakeHand", "get", data, login_2);
	}
	//客户端登录握手第二步
	function login_2(data){
		//data即从服务器中拿到的公钥
		//如果确实拿到了公钥,则将其set进encrypt成员中,以便之后的对称加密
		if(data.publicKey!=null&&data.summary!=null){
	        encrypt.setPublicKey(data.publicKey);
		}
		//对数据完整性做检查
		if(encrypt.encrypt_MD5(data.publicKey)==data.summary){
			//使用公钥对对称秘钥进行非对称加密加密
			var encryptKey=encrypt.encrypt_RSA(Common.key.value);
			//使用3DES对管理员账号进行加密
			var encryptAdminUserAccount=encrypt.encrypt_3DES($("#useraccount").val());
			//使用3DES对管理员密码进行加密
			var encryptAdminUserPassword=encrypt.encrypt_3DES($("#password").val());
			//将以上三项加密后的数据都放入一个json里面
			//下面的键值为了安全都使用了简写,"u"表示useraccount,"p"表示password,"k"表示key(对称秘钥)
			var formData={
				"u":encryptAdminUserAccount,
				"p":encryptAdminUserPassword,
				"k":encryptKey
			};
			//将formData转换成字符串后进行MD5加密以用于后续服务器校验数据完整性 
			var encryptCheckEncode=encrypt.encrypt_MD5(JSON.stringify(formData));
			//将formData转换成字符串后放入一个新的json中,这个json就是用来传给服务器的了
			var data={
				"type":Common.ENCTYPT_DATA,//这个type标志证明这个请求时包含了账号、密码和对称秘钥的信息
				"message":JSON.stringify(formData),
				"summary":encryptCheckEncode
			};
			Common.ajax("admin/shakeHand", "post", data, login_3);
		}else{
			$("#errInfo").html("登陆失败，请刷新后重试");
			$("#error").show();
		}
	};
	
	//客户端登录握手第三步
	function login_3(data){
		//data是服务器返回的关于登录的结果
		//首先获取是否登录成功的标识
		var result=data.successed;
		if(result){
			//如果登陆成功,则提交表单,进入管理员的首页
			$("#admin_login_form").submit();
		}
		else{
			//如果登录失败,则把原因打印出来
			$("#errInfo").html(data.errInfo);
			$("#error").show();
		}
	};
	
	return Login;
}());

/**
 * 加密的具体算法类,无论是rsa还是3des都封装进来了
 */
var Encrypt = (function(){
	function Encrypt(){};
	
	//这是rsa加密的对象,JSEncrypt是第三方库的一个对象
	var encrypt = new JSEncrypt();
	
	//设置当前rsa的公钥,用于之后的加密
	Encrypt.prototype.setPublicKey = function(publicKey){
		encrypt.setPublicKey(publicKey);
	};
	
	//rsa加密函数,实际上就是调用了第三方库进行加密
	Encrypt.prototype.encrypt_RSA = function(text){
		return encrypt.encrypt(text);
	};
	
	//3des加密函数,实际上就是调用了第三方库进行加密
	Encrypt.prototype.encrypt_3DES = function(text){
		return DES3.encrypt(Common.key.value,text);
	};
	//MD5摘要函数，实际上就是调用了第三方库进行摘要
	Encrypt.prototype.encrypt_MD5 = function(text){
		return $.md5(text);
	};
	return Encrypt;
}());