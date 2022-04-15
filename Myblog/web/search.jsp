<%@ page import="java.util.List" %>
<%@ page import="entity.Articles" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Destiny
  Date: 2020/9/15
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="gb2312">
    <title>首页_杨青个人博客 - 一个站在web前端设计之路的女技术员个人博客网站</title>
    <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青"/>
    <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/m.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.easyfader.min.js"></script>
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
<body>
<header>
    <div class="topnav">
        <nav>
            <ul>
                <div class="search" style="float: left">
                    <form action="index.do" method="post" name="searchform" id="searchform">
                        <input name="keyboard" id="keyboard" class="input_text" value="请输入关键字"
                               style="color: rgb(153, 153, 153);"
                               onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}"
                               onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
                        <input name="show" value="title" type="hidden">
                        <input name="tempid" value="1" type="hidden">
                        <input name="tbname" value="news" type="hidden">
                        <input name="search" class="input_submit" value="搜索" type="submit">
                    </form>
                </div>
                <li><a href="index.jsp">博客首页</a></li>
                <li><a href="image.do">我的相册</a></li>
                <li><a href="link.html">博客导航</a></li>
                <li><a href="about.jsp">关于我</a></li>
                <li><a href="login.jsp" style="float: right">登录</a></li>
                <li style="float: right">/</li>
                <li><a href="register.jsp" style="float: right">注册</a></li>
            </ul>
        </nav>
    </div>
</header>
<article>
    <div class="banner">
        <div id="sucaihuo" class="fader"><img class="slide" src="images/banner01.jpg"> <img class="slide"
                                                                                            src="images/banner02.jpg">
            <img class="slide" src="images/banner03.jpg">
            <div class="fader_controls">
                <div class="page prev" data-target="prev">&lsaquo;</div>
                <div class="page next" data-target="next">&rsaquo;</div>
                <ul class="pager_list">
                </ul>
            </div>
        </div>
        <script>
            $(function () {
                $('#sucaihuo').easyFader();
            });
        </script>
    </div>
    <div class="newblogs">
        <h2 class="hometitle">搜索结果</h2>
        <ul>
            <%
                List<Articles> articles = (List<Articles>) session.getAttribute("articles");
                for (int i = 0; i < articles.size(); i++) {
            %>
            <li>
                <h3 class="blogtitle"><span>
          <a href="/jstt/css3/" title="css3" target="_blank" class="classname"><%=articles.get(i).getCategory()%></a>
        </span>
                    <a href="/jstt/css3/2018-03-26/812.html" target="_blank"><%=articles.get(i).getAtitle()%>
                    </a>
                </h3>
                <div class="bloginfo"><span class="blogpic">
          <a href="/jstt/css3/2018-03-26/812.html" title="帝国cms 首页或者列表无图，不使用默认图片的方法">
            <img src="images/<%=articles.get(i).getArticleimage()%>" alt="帝国cms 首页或者列表无图，不使用默认图片的方法"/>
          </a></span>
                    <p><%=articles.get(i).getContents()%>
                    </p>
                </div>
                <div class="autor"><span class="lm f_l"></span>
                    <span class="dtime f_l"><%=articles.get(i).getPublishdate()%></span>
                    <span class="viewnum f_l">浏览（<a href="/"><%=articles.get(i).getReadnum()%></a>）</span>
                    <span class="f_r"><a href="/jstt/css3/2018-03-26/812.html" class="more">阅读原文>></a></span></div>
                <div class="line"></div>
            </li>
            <%}%>
        </ul>
    </div>
    <footer>
        <p>Design by <a href="/">杨青个人博客</a> <a href="/">蜀ICP备11002373号-1</a></p>
    </footer>
</article>
</body>
</html>
