package dao;

import entity.Images;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImagesDao {
    public List<Images> findAll() throws SQLException {
        //1、创建一个用来放入数据的集合
        List<Images> images = new ArrayList<>();
        //2、获取连接
        Connection conn = ConnectionUtils.getConnection();
        //3、书写你要发送给仓库的命令
        String sql = "select imgid,imgtitle,introduce,image from images";
        //4、通过连接构建一个载体，这个载体就是用来加载这个命令的
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //5、启动载体，执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、对结果集来进行循环判断
        while (resultSet.next()) {
            //8、如果下一行有数据，则取出这一行里面的每一列到Users对象里面来
            //8.1、创建一个Users对象
            Images i = new Images();
            //8.2、拿出每一列放入到users对象中来
            i.setImgid(resultSet.getInt(1));
            i.setImgtitle(resultSet.getString(2));
            i.setIntroduce(resultSet.getString(3));
            i.setImage(resultSet.getString(4));
            //8.3、把Users对象放入到集合中去
            images.add(i);
        }
        return images;
    }
}
