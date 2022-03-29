package servlet;

import dao.ArticlesDao;
import dao.CommentsDao;
import dao.ImagesDao;
import dao.UsersDao;
import entity.Articles;
import entity.Comments;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("*.do")
public class ServletController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //2、拿到请求名
        String requestName = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1, req.getRequestURI().lastIndexOf("."));
        //3、根据不同的请求进行不同的逻辑派发
        if ("login".equals(requestName)) {
            try {
                loginService(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("reg".equals(requestName)) {
            regService(req, resp);
        } else if ("checkUsername".equals(requestName)) {
            try {
                checkService(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("index".equals(requestName)) {
            try {
                articleindex(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("image".equals(requestName)) {
            try {
                imagepage(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("comment".equals(requestName)) {
            try {
                commentpage(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("addcomment".equals(requestName)) {
            addcommentpage(req, resp);
        } else if ("search".equals(requestName)) {
            try {
                searchpage(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void searchpage(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String atitle = req.getParameter("atitcle");
        //2、封装成一个Users对象
        ArticlesDao articlesDao = new ArticlesDao();
        //1、把指定表的所有数据查询到，然后绑定到session里面去
        req.getSession().setAttribute("articles", articlesDao.findByquery(atitle));
        //5、登录成功，进入主页面
    }

    private void addcommentpage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1、获取到你评论的用户名，日期和内容
        String cusername = req.getParameter("cusername");
        String cdate = req.getParameter("cdate");
        String commentcontents = req.getParameter("commentcontents");
        //2、封装成一个Comments对象
        Comments comments = new Comments();
        comments.setCusername(cusername);
        comments.setCdate(cdate);
        comments.setCommentcontents(commentcontents);
        //3、创建一个操作数据库的dao对象
        CommentsDao commentsDao = new CommentsDao();
        //4、调用增加评论方法
        try {
            commentsDao.addComments(comments);
            //5、评论成功，刷新页面
            resp.sendRedirect("content.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void commentpage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        CommentsDao commentsDao = new CommentsDao();
        //1、把指定表的所有数据查询到，然后绑定到session里面去
        req.getSession().setAttribute("comments", commentsDao.findAll());
        //5、查询数据展示出来
        resp.sendRedirect("comment.jsp");
    }

    private void imagepage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        ImagesDao imagesDao = new ImagesDao();
        //1、把指定表的所有数据查询到，然后绑定到session里面去
        req.getSession().setAttribute("images", imagesDao.findAll());
        //5、登录成功，进入主页面
        resp.sendRedirect("image.jsp");
    }

    private void articleindex(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        ArticlesDao articlesDao = new ArticlesDao();
        //1、把指定表的所有数据查询到，然后绑定到session里面去
        req.getSession().setAttribute("articles", articlesDao.findAll());
        //5、登录成功，进入主页面
        resp.sendRedirect("index.jsp");
    }

    private void checkService(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //1、获取到你登录输入的用户名和密码
        String username = req.getParameter("uname");
        //2、获取一个传输数据到前台的PrintWriter对象
        PrintWriter printWriter = resp.getWriter();
        //3、创建一个操作数据库的dao对象
        UsersDao usersDao = new UsersDao();
        //4、调用检查用户名方法
        if (usersDao.checkUsername(username)) {
            //5、如果用户名不存在，发送数据给前台
            printWriter.write("用户名可以使用");
        } else {
            //6、如果用户名已经存在，发送数据给前台
            printWriter.write("用户名已存在");
        }
    }

    private void regService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1、获取到你登录输入的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2、封装成一个Users对象
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        //3、创建一个操作数据库的dao对象
        UsersDao usersDao = new UsersDao();
        //4、调用注册方法
        try {
            usersDao.reg(users);
            //5、注册成功，进入登录
            resp.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            //6、注册失败，回到当前页面
            resp.sendRedirect("register.jsp");
        }
    }

    private void loginService(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        //1、获取到你登录输入的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2、封装成一个Users对象
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        //3、创建一个操作数据库的dao对象
        UsersDao usersDao = new UsersDao();
        //4、调用登录方法
        if (usersDao.login(users)) {
//            //6、把用户名绑定到session中
//            req.getSession().setAttribute("realname",username);
            //7、把指定表的所有数据查询到，然后绑定到session里面去
            //5、登录成功，进入主页面
            resp.sendRedirect("index.jsp");
        } else {
            //6、如果登录失败，那么加载一个错误信息，传回登录页面
//            req.setAttribute("error_msg","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
