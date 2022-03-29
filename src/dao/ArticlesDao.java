package dao;

import entity.Articles;
import entity.Users;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDao {
    public List<Articles> findAll() throws SQLException {
        //1、创建一个用来放入数据的集合
        List<Articles> articles = new ArrayList<>();
        //2、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //3、书写你要发送给仓库的命令
        String sql = "select aid,category,atitle,contents,publishdate,readnum,articleimage from articles";
        //4、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、对结果集来进行循环判断
        while (resultSet.next()) {
            //8、如果下一行有数据，则取出这一行里面的每一列到Users对象里面来
            //8.1、创建一个Users对象
            Articles a = new Articles();
            //8.2、拿出每一列放入到users对象中来
            a.setAid(resultSet.getInt(1));
            a.setCategory(resultSet.getString(2));
            a.setAtitle(resultSet.getString(3));
            a.setContents(resultSet.getString(4));
            a.setPublishdate(resultSet.getString(5));
            a.setReadnum(resultSet.getInt(6));
            a.setArticleimage(resultSet.getString(7));
            //8.3、把Users对象放入到集合中去
            articles.add(a);
        }
        return articles;
    }

    public List<Articles> findByquery(String atitle) throws SQLException {
        //1、创建一个用来放入数据的集合
        List<Articles> articles = new ArrayList<>();
        //2、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //3、书写你要发送给仓库的命令
        String sql = "select aid,category,atitle,contents,publishdate,readnum,articleimage from articles where atitle like ?";
        //4、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、如果有问号，进行问号的填充
        preparedStatement.setString(1, atitle);
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、对结果集来进行循环判断
        while (resultSet.next()) {
            //8、如果下一行有数据，则取出这一行里面的每一列到Users对象里面来
            //8.1、创建一个Users对象
            Articles a = new Articles();
            //8.2、拿出每一列放入到users对象中来
            a.setAid(resultSet.getInt(1));
            a.setCategory(resultSet.getString(2));
            a.setAtitle(resultSet.getString(3));
            a.setContents(resultSet.getString(4));
            a.setPublishdate(resultSet.getString(5));
            a.setReadnum(resultSet.getInt(6));
            a.setArticleimage(resultSet.getString(7));
            //8.3、把Users对象放入到集合中去
            articles.add(a);
        }
        return articles;
    }
}
