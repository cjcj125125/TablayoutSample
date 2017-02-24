var combi_code = Common.getParam("combi_code"),uid = Common.getParam("uid");//判断
var regId="";//Android获得regid方法
var userid="";
var userRole="";

function getRegid(id){
	regId = id;
}

//登录页
var login = function($){
    $("#loginbtn").click(function(){//登录
    	regId = window.first.getRegId();
        Common.showLoading();//显示加载中效果图片
        var account =  $("#account").val(),
             pwd = $("#password").val(),
             regMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/,//判断手机号码的正则表达式
             regPassword = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;//密码正则表达式 8-16位 数字、字母、字符至少包含二种
        if(!account){//判断账号是否为空
            Common.showAlert({message:"账号不能为空！",css:{width:"120px"}});
            setTimeout(function(){
                $("#account").focus();
                Common.closeAlert();
            },1000);
            return;
        }
        if(!regMobile.test(account)){//判断输入的账号是否符合要求
            Common.showAlert({message:"请输入正确的手机号！",css:{width:"120px"}});
            setTimeout(function(){
                $("#account").val("").focus();
                Common.closeAlert();
            },1000);
            return;
        }
        if(!pwd){//判断密码是否为空
            Common.showAlert({message:"密码不能为空！",css:{width:"120px"}});
            setTimeout(function(){
                $("#password").focus();
                Common.closeAlert();
            },1000);
            return;
        }
        if(!regPassword.test(pwd)){//判断输入的密码是否符合要求
            Common.showAlert({message:"输入的密码不合规，必须是8-16位数字、字母、字符且至少包含二种",css:{width:"120px"}});
            setTimeout(function(){
                $("#password").val("").focus();
                Common.closeAlert();
            },1200);
            return;
        }
        if(regId=="" || regId==null){
        	Common.showAlert("登录失败,请重新登录");
        	setTimeout(function(){
        		window.first.jPushinit();
        		Common.closeAlert();
        	},1500);
        	return;
        }
        Common.ajax({//获取加密后的密码
            url: "account/common/password_rsa",
            type: "post",
            dataType: "json",
            data: {iPassword: $("#password").val()},
            callback: function (data) {
                if(data.error_no=="0000"){
                    var rsaPwd  = data.rsaPassword;
			        Common.ajax({
			            url:"account/common/login",
			            type:"post",
			            dataType:"json",
			            data:{iPhone:$("#account").val(),iPassword:rsaPwd,reg_id:regId},
			            callback:function(data){
			                if (data.error_no=="0000") {
			                //Android自动登录
			                    if($("#rmbuser").attr("checked")){
			                        autoL = "1";
			                        window.first.setSharedPreference(autoL,account,rsaPwd);
			                    }else{
									autoL = "0";
			                        window.first.setSharedPreference(autoL,account,rsaPwd);
			                    }
			                	userid=data.data.userId;
			                	userRole=data.data.role+"";
			                	window.first.saveUser_ID(userid);
			                	window.first.getRole(userRole);
			                    Common.showAlert({message:"登录成功！",css:{width:"120px"}});
			                    setTimeout(function(){
			                        Common.closeAlert();
			                        //判断上一页面并跳转
			                        var home = /.*\/index/.test(document.referrer),//主页
			                            my = /.*\/account\/personalCenter/.test(document.referrer),//个人中心
			                            md = /.*\/portfolio\/masterDetail/.test(document.referrer),//达人详情
			                            pd = /.*\/portfolio\/portfolioDetail/.test(document.referrer);//组合详情
			                        if(home){
			                            window.location.replace("file:///android_asset/html/index.html");
			                        }else if(my){
			                            /*if(data.data.role=="0"){
			                                window.location.replace("file:///android_asset/html/account/personalCenter_normal.html");
			                            }else{
			                                window.location.replace("file:///android_asset/html/account/personalCenter_master.html")
			                            }*/
			                        	window.location.replace("file:///android_asset/html/account/personalCenter_normal.html");
			                        }else if(md){
			                            window.location.replace("file:///android_asset/html/portfolio/masterDetail.html");
			                        }else if(pd){
			                            window.location.replace("file:///android_asset/html/portfolio/portfolioDetail.html?combi_code="+combi_code+"&uid="+uid+"");
			                        }else{
			                           window.location.replace("file:///android_asset/html/index.html");
			                        	//window.location.replace("file:///android_asset/html/account/personalCenter_normal.html");
			                        	//window.first.finish();
			                        }
			                    },1500);
			                } else {
			                    Common.showAlert({message:data.error_info,css:{width:"120px"}});
			                    setTimeout(function(){
			                        Common.closeAlert();
			                        $("#password").val("").focus();
			                    },1000);
			                }
			            }
			        });
                }else{
                    Common.showAlert({message: data.error_info,css:{width:"120px"}});
                }
            }
        });
    });
    
    //注册按钮
    $("#registerbtn").click(function(){
        window.location.href="file:///android_asset/html/register.html";
    });

    //忘记密码
    $("#forgetpwd").click(function(){
        window.location.href="file:///android_asset/html/account/resetPassword.html";
    });

    //微信登录
    $("#weixin").click(function(){
    	window.first.wechat_login();
    });

    //随便逛逛
    $("#lookaround").click(function(){
        window.location.href="file:///android_asset/html/suibianguangguang.html";
    });
    
  //账号、密码输入框禁止输入非数字方法
   $("#account").on("input",function(){
		var n=$("#account").val();
		if(/[^\d]/.test(n)){//替换非数字字符
			var temp_amount=n.replace(/[^\d]/g,'');
			$("#account").val(temp_amount);
		}
	});
   $("#password").on("input",function(){
		var l=$(this);
		if(/[\u4e00-\u9fa5]/.test(l.val())){
			var tem=l.val().replace(/[\u4e00-\u9fa5]/g,'');
			$(this).val(tem);
		}
	});

   /* //判断是否自动登录
    function isAutoLogin(){//原生调用该方法确认是否勾选自动登录
    	if($("#rmbuser").attr("checked",'true')){

            var autoLogin = [true,$("#account").val(),$("#password").val()];
        }else{

            var autoLogin = [false,$("#account").val(),$("#password").val()];
        }
         return autoLogin;
     }

    }*/

}($);
