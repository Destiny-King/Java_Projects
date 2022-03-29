package dao;

import entity.Users;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    public void reg(Users users) throws SQLException {
        //1、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "insert into users(username,password,name) values(?,?,?)";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、把问号进行填充
        preparedStatement.setString(1, users.getUsername());
        preparedStatement.setString(2, users.getPassword());
        preparedStatement.setString(3, users.getName());
        //5、启动载体，执行sql语句
        preparedStatement.execute();
    }

    public boolean login(Users users) throws SQLException {
        //1、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "select * from users where username=? and password=?";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、把问号进行填充
        preparedStatement.setString(1, users.getUsername());
        preparedStatement.setString(2, users.getPassword());
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //6、对结果集来进行判断,如果有数据，就证明登录成功，返回true
        if (resultSet.next()) {
            return true;
        }
        //7、如果前面没返回true，意味着用户名密码错误，这里返回false
        return false;

    }

    public List<Users> findAll() throws SQLException {
        //1、创建一个用来放入数据的集合
        List<Users> users = new ArrayList<>();
        //2、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //3、书写你要发送给仓库的命令
        String sql = "select id,username,password,name from users";
        //4、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、对结果集来进行循环判断
        while (resultSet.next()) {
            //8、如果下一行有数据，则取出这一行里面的每一列到Users对象里面来
            //8.1、创建一个Users对象
            Users u = new Users();
            //8.2、拿出每一列放入到users对象中来
            u.setId(resultSet.getInt(1));
            u.setUsername(resultSet.getString(2));
            u.setPassword(resultSet.getString(3));
            u.setName(resultSet.getString(4));
            //8.3、把Users对象放入到集合中去
            users.add(u);
        }
        return users;
    }

    public boolean checkUsername(String username) throws SQLException {
        //1、获取连接
        Connection connection = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "select * from users where username=?";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //4、如果有问号，进行问号的填充
        preparedStatement.setString(1, username);
        //5、执行载体，因为是查询，所以返回一个结果集对象-->ResultSet
        ResultSet resultSet = preparedStatement.executeQuery();
        //6、对结果集来进行判断,如果有数据，就证明登录成功，返回true
        if (resultSet.next()) {
            return false;
        }
        //7、如果前面没返回true，意味着用户名密码错误，这里返回false
        return true;
    }


    public void updateUsers(Users users, int id) throws SQLException {
        //1、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "update users set username=?,password=?,name=? where id=?";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、把问号进行填充
        preparedStatement.setString(1, users.getUsername());
        preparedStatement.setString(2, users.getPassword());
        preparedStatement.setString(3, users.getName());
        preparedStatement.setInt(4, users.getId());
        //5、启动载体，执行sql语句
        preparedStatement.execute();
    }

    public void deleteUsers(int id) throws SQLException {
        //1、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //2、书写你要发送给仓库的命令
        String sql = "delete from users where id=?";
        //3、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //4、把问号进行填充
        preparedStatement.setInt(1, id);
        //5、启动载体，执行sql语句
        preparedStatement.execute();
    }
}
