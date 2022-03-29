<%@ page import="entity.Articles" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Images" %><%--
  Created by IntelliJ IDEA.
  User: mxfallen
  Date: 2020/9/16
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="gb2312">
    <title>我的相册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/share.css" rel="stylesheet">
    <link href="css/m.css" rel="stylesheet">
    <script src="js/scrollReveal.js"></script>
    <!--[if lt IE 9]>
    <script src="js/modernizr.js"></script>
    <![endif]-->
    <script>
        window.onload = function () {
            var oH2 = document.getElementsByTagName("h2")[0];
            var oUl = document.getElementsByTagName("ul")[0];
            oH2.onclick = function () {
                var style = oUl.style;
                style.display = style.display == "block" ? "none" : "block";
                oH2.className = style.display == "block" ? "open" : ""
            }
        }
    </script>
</head>
<%List<Articles> articles = (List<Articles>) session.getAttribute("articles");%>
<%List<Images> images = (List<Images>) session.getAttribute("images");%>
<body>
<header>
    <div id="mnav">
        <h2><span class="navicon"></span></h2>
        <ul>
            <li><a href="index.jsp">博客首页</a></li>
            <li><a href="image.do">我的相册</a></li>
            <li><a href="link.html">博客导航</a></li>
            <li><a href="about.jsp">关于我</a></li>
            <li><a href="login.html" style="float: right">登录</a></li>
            <li style="float: right">/</li>
            <li><a href="register.html" style="float: right">注册</a></li>
        </ul>
    </div>
    <nav>
        <ul>
            <li><a href="index.jsp">博客首页</a></li>
            <li><a href="image.do">我的相册</a></li>
            <li><a href="link.html">博客导航</a></li>
            <li><a href="about.jsp">关于我</a></li>
            <li><a href="login.html" style="float: right">登录</a></li>
            <li style="float: right">/</li>
            <li><a href="register.html" style="float: right">注册</a></li>
        </ul>
    </nav>
</header>
<article>
    <h2 class="litle"><span>好咖啡要和朋友一起品尝，好“风景”也要和同样喜欢它的人一起分享。</span>我的相册</h2>
    <div class="navtab">
        <ul class="navlist" id="pageContent">
            <li><a href="#">个人图片</a></li>
            <li><a href="#">风景最照</a></li>
        </ul>
        <script language="javascript">
            var obj = null;
            var As = document.getElementById('pageContent').getElementsByTagName('li');
            obj = As[0];
            for (i = 1; i < As.length; i++) {
                if (window.location.href.indexOf(As[i].href) >= 0)
                    obj = As[i];
            }
            obj.id = 'pagecurrent'
        </script>
    </div>
    <div class="mbbox">
        <%
            for (int i = 0; i < articles.size(); i++) {
        %>
        <div class="mbpic effect" data-scroll-reveal="enter from the bottom">
            <div class="mbimg"><img src="images/<%=images.get(i).getImage()%>" alt="超级单身派对"/></div>
            <div class="mbtitle"><a href="#"><%=images.get(i).getImgtitle()%>
            </a></div>
            <p class="mbinfo"><%=images.get(i).getIntroduce()%>
            </p>
        </div>
        <%}%>
    </div>
    <div class="pagelist"><a title="Total record">&nbsp;<b>10</b> </a>&nbsp;&nbsp;&nbsp;<b>1</b>&nbsp;<a href="#">2</a>&nbsp;<a
            href="#">下一页</a>&nbsp;<a href="#">尾页</a></div>
</article>
<footer>
    <p>Design by <a href="#">超级单身派对</a></p>
</footer>
</body>
</html>
<script>
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
        (function () {
            window.scrollReveal = new scrollReveal({reset: true});
        })();
    }
    ;
</script>
