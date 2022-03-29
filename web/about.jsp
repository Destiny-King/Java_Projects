<%@ page import="entity.Articles" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mxfallen
  Date: 2020/9/16
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="gb2312">
    <title>关于我</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/about.css" rel="stylesheet">
    <link href="css/m.css" rel="stylesheet">
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
    <h2 class="litle"><span>像“草根”一样，紧贴着地面，低调的存在，冬去春来，枯荣无恙。</span>关于我</h2>
    <div class="ab_box">
        <div class="leftbox">
            <div class="newsview">
                <div class="news_infos">
                    <p>王泽祺，男，一个90后草根男站长！17年入行。一直潜心研究web前端技术，一边工作一边积累经验，分享一些个人博客模板，以及SEO优化等心得。我入行早，大家也亲切的叫我&ldquo;祺姐&rdquo;。<br/>
                        <br/>
                        SEO奋斗了将近两年时间，个人博客网站百度排名也从之前的30页后，排到了第一页。期间有很多的不容易，但是都没有放弃过。入了这一行，就深深的喜欢上它。我喜欢一句话&ldquo;冥冥中该来则来，无处可逃&rdquo;。<br/>
                        <br/>
                        近几年我也发现个人博客排前几页的也有很多是我做过的模板，感谢各位站长的欣赏，我仔细看过他们的网站。他们在我原模板的基础上有修改，而且他们做的原创内容都是值得一读的。有时候甚至排名都超过了我个人博客网站的排名。<br/>
                        <br/>
                        现在很多人向我请教如何做好SEO，我想说的是，一是&ldquo;代码&rdquo;，一定要简单，布局要合理。二是&ldquo;内容&rdquo;，一定要有原创，伪原创也是可以的。三是&ldquo;持续&rdquo;，这是一个持续性过程，一定要有耐心，SEO不是马上生效的。<br/>
                        <br/>
                </div>
            </div>
            <div class="meandblog">
                <h2>我和我的博客</h2>
                <ul>
                    <%
                        for (int i = 0; i < 4; i++) {
                    %>
                    <li><a href="content.jsp?aid=<%=i%>" target="_blank"><%=articles.get(i).getAtitle()%>
                    </a></li>
                    <%}%>
                </ul>
            </div>
        </div>
        <div class="rightbox">
            <div class="aboutme">
                <h2 class="hometitle">关于我</h2>
                <div class="avatar"><img src="images/b04.jpg"></div>
                <div class="ab_con">
                    <p>网名：林深见鹿 | 王泽祺</p>
                    <p>职业：Web前端设计师、网页设计 </p>
                    <p>个人微信：wangzeqi_1987</p>
                    <p>邮箱：wangzeqi@qq.com</p>

                </div>
            </div>
            <div class="weixin">
                <h2 class="hometitle">我的微信</h2>
                <ul>
                    <img src="images/wx.png">
                </ul>
            </div>
        </div>
    </div>
</article>
<footer>
    <p>Design by <a href="#">超级单身派对</a></p>
</footer>
</body>
</html>

