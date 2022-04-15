<%@ page import="entity.Articles" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Comments" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<%List<Articles> articles = (List<Articles>) session.getAttribute("articles");%>
<%int aid = Integer.parseInt(request.getParameter("aid"));%>
<head>
    <meta charset="utf-8">
    <title><%=articles.get(aid).getAtitle()%>
    </title>
    <meta name="keywords" content="超级单身派对"/>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/info.css" rel="stylesheet">
    <link href="css/m.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="js/modernizr.js"></script>
    <![endif]-->
    <style>
        .mytextarea {
            width: 100%;
            overflow: auto;
            word-break: break-all;
            height: 100px;
            color: #000;
            font-size: 1em;
            resize: none;
        }

        .pull-right {
            float: right !important;
        }

        .btn-info {
            color: #fff;
            background-color: #5bc0de;
            border-color: #46b8da;
        }

        .btn {
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        .comment-list {
            width: 900px;
            margin: 20px auto;
            clear: both;
            padding-top: 20px;
            display: block;
        }

        .comment-list .comment-info {
            position: relative;
            margin-bottom: 20px;
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            display: block;
        }

        .comment-list .comment-info header {
            width: 10%;
            position: absolute;
        }

        .comment-list .comment-info .comment-right {
            padding: 5px 0px 5px 11%;
        }

        .comment-list .comment-info .comment-right h3 {
            margin: 5px 0px;
            font-size: 24px;
            font-family: inherit;
            font-weight: 500;
            line-height: 1.1;
            color: inherit;
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 0px;
            margin-inline-end: 0px;
        }

        .comment-list .comment-info .comment-right .comment-content-header {
            height: 25px;
        }

        .comment-list .comment-info .comment-right .comment-content-header span, .comment-list .comment-info .comment-right .comment-content-footer span {
            padding-right: 2em;
            color: #aaa;
            cursor: pointer;
        }

        .comment-list .comment-info .comment-right .reply-list {
            border-left: 3px solid #ccc;
            padding-left: 7px;
        }
    </style>
</head>
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
    <div class="topnav">
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
    </div>
</header>
<article>

    <div class="infos">
        <div class="newsview">
            <h2 class="intitle"><a href="index.jsp">首页</a>&nbsp;>&nbsp;<a href="content.jsp">博客</a></h2>
            <h3 class="news_title"><%=articles.get(aid).getAtitle()%>
            </h3>
            <div class="news_author">
                <span class="au01"></span>
                <span class="au02"><%=articles.get(aid).getPublishdate()%></span>
                <span class="au03">
                    共
                    <b><%=articles.get(aid).getReadnum()%></b>
                    人围观
                </span></div>
            <div class="tags">
                <a href="#" target="_blank"><%=articles.get(aid).getCategory()%>
                </a>
            </div>
            <div class="news_about">
                <strong>简介</strong>
                <%=articles.get(aid).getContents().substring(0, 50)%>
            </div>
            <div class="news_infos">
                <img src="images/<%=articles.get(aid).getArticleimage()%>"/>
                <%=articles.get(aid).getContents()%>
            </div>
        </div>
    </div>

    <div class="nextinfo">
        <p><a href="content.jsp?aid=<%=aid%>">上一篇：<%=articles.get(aid).getAtitle()%>
        </a>
        </p>
        <p><a href="content.jsp?aid=<%=aid+1%>">下一篇：<%=articles.get(aid + 1).getAtitle()%>
        </a></p>
    </div>
    <div class="otherlink">
        <h2>相关文章</h2>
        <ul>
            <li><a href="content.jsp?aid=<%=1%>"
                   title=<%=articles.get(aid).getAtitle()%>><%=articles.get(1).getAtitle()%>
            </a></li>
            <li><a href="content.jsp?aid=<%=2%>"
                   title=<%=articles.get(aid).getAtitle()%>><%=articles.get(2).getAtitle()%>
            </a></li>
        </ul>
    </div>
    <div class="news_pl">
        <h2>文章评论</h2>
        <br>
        <ul>
            <!-- 评论 开始 -->
            <form action="addcomment.do" method="post">
                <div class="pinglun">
                    <textarea id="content" class="mytextarea" placeholder="请写下你的评论"></textarea>
                    <button class="btn btn-info pull-right" id="comment" type="submit">评论</button>
                </div>
            </form>
            <%
                List<Comments> comments = (List<Comments>) session.getAttribute("comments");
                if (comments != null) {
                    for (int i = 0; i < comments.size(); i++) {
            %>
            <div class="comment-list">
                <div class="comment-info">
                    <header><img src="images/1481101906.png"></header>
                    <div class="comment-right">
                        <h3><%=comments.get(i).getCusername()%>
                        </h3>
                        <div class="comment-content-header">
                            <span><%=comments.get(i).getCdate()%></span>
                        </div>
                        <p class="content"><%=comments.get(i).getCommentcontents()%>
                        </p>
                        <div class="reply-list">回复</div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <%
                }
            %>
            <!-- 评论 结束 -->
        </ul>
    </div>
</article>
<footer>
    <p>Design by <a href="#">超级单身派对</a></p>
</footer>
</body>
</html>