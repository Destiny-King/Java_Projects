<%@ page import="entity.Articles" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Images" %><%--
  Created by IntelliJ IDEA.
  User: mxfallen
  Date: 2020/9/16
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="gb2312">
    <title>首页</title>
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
<%List<Articles> articles = (List<Articles>) session.getAttribute("articles");%>
<%List<Images> images = (List<Images>) session.getAttribute("images");%>
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
                        <input name="submit" class="input_submit" value="搜索" type="submit" onclick="search.jsp">
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
    <div class="blog">
        <%
            for (int i = 0; i < 4; i++) {
        %>
        <figure>
            <ul>
                <a href="content.jsp?aid=<%=i%>">
                    <img src="images/<%=articles.get(i).getArticleimage()%>" alt="超级单身派对"/>
                    <span>查看博客</span>
                </a>
            </ul>
            <p><a href="content.jsp?aid=<%=i%>"><%=articles.get(i).getAtitle()%>
            </a></p>
            <figcaption><%=articles.get(i).getContents().substring(0, 50)%>
            </figcaption>
        </figure>
        <%}%>

    </div>
    <div class="newblogs">
        <h2 class="hometitle">最新文章</h2>
        <ul>
            <%
                for (int i = 0; i < articles.size(); i++) {
            %>
            <li>
                <h3 class="blogtitle">
                    <span><a href="content.jsp?aid=<%=i%>" target="_blank"
                             class="classname"><%=articles.get(i).getCategory()%></a></span>
                    <a href="content.jsp?aid=<%=i%>" target="_blank"><%=articles.get(i).getAtitle()%>
                    </a>
                </h3>
                <div class="bloginfo">
                    <span class="blogpic">
                    <a href="content.jsp?aid=<%=i%>">
                        <img src="images/<%=articles.get(i).getArticleimage()%>" alt="超级单身派对"/>
                    </a></span>
                    <p><%=articles.get(i).getContents().substring(0, 50)%>
                    </p>
                </div>
                <div class="autor">
                    <span class="lm f_l"></span>
                    <span class="dtime f_l">
                        <%=articles.get(i).getPublishdate()%>
                    </span>
                    <span class="viewnum f_l">
                        浏览（<a href="/"><%=articles.get(i).getReadnum()%></a>）
                    </span>
                    <span class="f_r"><a href="content.jsp?aid=<%=i%>" class="more">阅读原文>>
                    </a></span>
                </div>
                <div class="line"></div>
            </li>
            <%}%>
        </ul>
    </div>
    <div class="rbox">
        <div class="paihang">
            <h2 class="hometitle">热门文章</h2>
            <ul>
                <%
                    for (int i = 0; i < 3; i++) {
                %>
                <li>
                    <b><a href="content.jsp?aid=<%=i%>" target="_blank"><%=articles.get(1).getAtitle()%>
                    </a></b>
                    <p>
                        <i><img src="images/<%=articles.get(i).getArticleimage()%>" alt="超级单身派对"/></i>
                        <%=articles.get(1).getContents().substring(0, 50)%>
                    </p>
                </li>
                <%}%>
            </ul>
        </div>

        <div class="paihang">
            <h2 class="hometitle">点击排行</h2>
            <ul>
                <%
                    for (int i = 0; i < 3; i++) {
                %>
                <li><b><a href="content.jsp?aid=<%=i%>" target="_blank"><%=articles.get(1).getAtitle()%>
                </a></b>
                    <p><i><img src="images/<%=articles.get(i).getArticleimage()%>" alt="超级单身派对"/></i>
                        <%=articles.get(1).getContents().substring(0, 50)%>
                    </p>
                </li>
                <%}%>
            </ul>
        </div>
        <div class="links">
            <h2 class="hometitle">友情链接</h2>
            <ul>
                <li><a href="image.do">我的相册</a></li>
                <li><a href="link.html">博客导航</a></li>
                <li><a href="about.jsp">关于我</a></li>
            </ul>
        </div>
        <div class="weixin">
            <h2 class="hometitle">我的微信</h2>
            <ul>
                <img src="images/wx.png">
            </ul>
        </div>
    </div>
</article>
<footer>
    <p>Design by <a href="#">超级单身派对</a></p>
</footer>
</body>
</html>

