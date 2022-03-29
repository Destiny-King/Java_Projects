package dao;

import entity.Comments;
import entity.Users;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentsDao {
    public List<Comments> findAll() throws SQLException {
        //1、创建一个用来放入数据的集合
        List<Comments> comments = new ArrayList<>();
        //2、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //3、书写你要发送给仓库的命令
        String sql = "select cid,cusername,cdate,commentcontents from comments";
        //4、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、对结果集来进行循环判断
        while (resultSet.next()) {
            //8、如果下一行有数据，则取出这一行里面的每一列到Users对象里面来
            //8.1、创建一个Users对象
            Comments c = new Comments();
            //8.2、拿出每一列放入到users对象中来
            c.setCid(resultSet.getInt(1));
            c.setCusername(resultSet.getString(2));
            c.setCdate(resultSet.getString(3));
            c.setCommentcontents(resultSet.getString(4));
            //8.3、把Users对象放入到集合中去
            comments.add(c);
        }
        return comments;
    }

    public void addComments(Comments comments) throws SQLException {
        //1、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "insert into comments(cusername,cdate,commentcontents) values(?,?,?)";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、把问号进行填充
        preparedStatement.setString(1, comments.getCusername());
        preparedStatement.setString(2, comments.getCdate());
        preparedStatement.setString(3, comments.getCommentcontents());
        //5、启动载体，执行sql语句
        preparedStatement.execute();
    }
}
