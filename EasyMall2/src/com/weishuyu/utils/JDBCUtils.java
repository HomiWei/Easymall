package com.weishuyu.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    public static ComboPooledDataSource source=new ComboPooledDataSource();
    private JDBCUtils(){}
    //获取连接
    public static Connection getConnection() throws SQLException {
        return  source.getConnection();
    }
    public static  void  close(Connection conn, Statement stat, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }
        if (stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                stat=null;
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }
    }
}
