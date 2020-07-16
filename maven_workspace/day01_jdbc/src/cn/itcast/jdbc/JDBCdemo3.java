package cn.itcast.jdbc;

import cn.itcast.domain.Emp;
import cn.itcast.utils.JDBCutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCdemo3 {
    public static void main(String[] args) {
        List<Emp> list = new JDBCdemo3().findAll2();
        System.out.println(list);
    }
    public List<Emp> findAll(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        List<Emp> list=new ArrayList<Emp>();
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.创建数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///stu", "root", "root");
            //3.定义sql语句
            String sql="select * from emp";
            //4.获取执行sql语句对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            Emp emp=null;

            while(rs.next()){
                int id = rs.getInt("id");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double bonus = rs.getDouble("bonus");
                double salary = rs.getDouble("salary");
                int dep_id = rs.getInt("dept_id");
                emp=new Emp();
                emp.setId(id);
                emp.setBonus(bonus);
                emp.setDep_id(dep_id);
                emp.setJob_id(job_id);
                emp.setJoindate(joindate);
                emp.setMgr(mgr);
                emp.setSalary(salary);
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }
    public List<Emp> findAll2(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        List<Emp> list=new ArrayList<Emp>();
        try {
            //1.注册驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.创建数据库连接对象
//            conn = DriverManager.getConnection("jdbc:mysql:///stu", "root", "root");
//            //3.定义sql语句
            conn= JDBCutils.getConnection();
            String sql="select * from emp";
            //4.获取执行sql语句对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            Emp emp=null;

            while(rs.next()){
                int id = rs.getInt("id");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double bonus = rs.getDouble("bonus");
                double salary = rs.getDouble("salary");
                int dep_id = rs.getInt("dept_id");
                emp=new Emp();
                emp.setId(id);
                emp.setBonus(bonus);
                emp.setDep_id(dep_id);
                emp.setJob_id(job_id);
                emp.setJoindate(joindate);
                emp.setMgr(mgr);
                emp.setSalary(salary);
                list.add(emp);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutils.close(rs,stmt,conn);

        }
        return list;
    }
}
