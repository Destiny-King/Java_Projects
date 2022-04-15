<%--
  Created by IntelliJ IDEA.
  User: Destiny
  Date: 2020/9/14
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script>
        $(function () {
            $("input[type='text']").blur(function () {
                $.ajax({
                    type: "POST",
                    url: "checkUsername.do",
                    data: {uname: $(this).val()},
                    success: function (data) {
                        $("span").text(data);
                    }
                });
            });
            //当我的鼠标重新放入到用户名的时候，此时，把span里面的内容清空
            $("input[type='text']").focus(function () {
                $("span").text("");
            })
        })
        //这个函数是在我点击注册按钮的时候触发，如果说你span标签里面的内容是可以使用，
        //就意味着这个用户名可用注册，所以点击注册按钮有效，否则不让你点击
        function check() {
            if ($("span").text() == "用户名可以使用") {
                return true;
            }
            return false;
        }
    </script>


</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">Register</h3>
            <form action="reg.do" method="post" onsubmit="return check()">
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username"
                               name="username" autofocus="autofocus" maxlength="20"/>
                        <span style="color: red"></span>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" id="password"
                               name="password" maxlength="8"/>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success">注册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
