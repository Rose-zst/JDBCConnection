package cn.itcast.jdbc;

//jdbc快速入门

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1.导入驱动jar包
        //2.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "root");
        //4.编写sql语句
        String sql="update account set balance=500 where id=1";
        //5.获取执行sql语句对象
        Statement stmt = conn.createStatement();
        //6.执行sql语句
        int count = stmt.executeUpdate(sql);
        //7.输出count
        System.out.println(count);
        //8.关闭资源
        stmt.close();
        conn.close();
    }
}
