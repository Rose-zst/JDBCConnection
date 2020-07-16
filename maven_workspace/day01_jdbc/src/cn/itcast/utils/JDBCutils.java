package cn.itcast.utils;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
* jdbc工具类
* */
public class JDBCutils {



    public static String url;
    public static String user;
    public static String password;
    public static String driver;
    //使用静态代码块，文件只需读取一次即可
    static {

        try {
            //读取资源文件，获取值
            //1.创建properties集合类
            Properties pro = new Properties();
            //2.加载文件
            //获取src路径下文件路径的方式 --》ClassLoader 类加载器
            ClassLoader classLoader = JDBCutils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            System.out.println(path);
            //pro.load(new FileInputStream(path));
            //
            pro.load(new FileInputStream(path));
            //3.获取数据，赋值
            url=pro.getProperty("url");
            System.out.println(pro.getProperty("url"));
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver=pro.getProperty("driver");

            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }
    public static void close(Statement stmt,Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs ,Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
