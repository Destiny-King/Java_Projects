<%--
  Created by IntelliJ IDEA.
  User: Destiny
  Date: 2020/9/14
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        .show {
            display: block;
        }

        .hide {
            display: none;
        }

        #embed-captcha {
            width: 500px;
            margin: 0 auto;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/gt.js"></script>
</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <form action="login.do" method="post">
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username"
                               name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <span style="color: #ff0000"></span>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" id="password"
                               name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <div id="embed-captcha"></div>
                        <p id="wait" class="show">正在加载验证码......</p>
                        <p id="notice" class="hide">请先拖动验证码到相应位置</p>
                        <br>
                        <button type="submit" class="btn btn-success" id="embed-submit">登录</button>
                    </div>
                </div>
            </form>
            <script>
                var handlerEmbed = function (captchaObj) {
                    $("#embed-submit").click(function (e) {
                        var validate = captchaObj.getValidate();
                        if (!validate) {
                            $("#notice")[0].className = "show";
                            setTimeout(function () {
                                $("#notice")[0].className = "hide";
                            }, 2000);
                            e.preventDefault();
                        }
                    });
                    // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
                    captchaObj.appendTo("#embed-captcha");
                    captchaObj.onReady(function () {
                        $("#wait")[0].className = "hide";
                    });
                    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
                };
                $.ajax({
                    // 获取id，challenge，success（是否启用failback）
                    url: "pc-geetest/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        // 使用initGeetest接口
                        // 参数1：配置参数
                        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
                        initGeetest({
                            gt: data.gt,
                            challenge: data.challenge,
                            product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                            offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
                        }, handlerEmbed);
                    }
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>
