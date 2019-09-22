package com.weishuyu.dao;

import com.weishuyu.domain.User;
import com.weishuyu.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkedDao {
    /**
     * 连接数据库，检查user中的username是否存在
     * 存在则返回false
     * @param user
     * @return
     */
    public static boolean reasonableUser(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= JDBCUtils.getConnection();
            ps=conn.prepareStatement("select * from  user where username=?");
            ps.setString(1,user.getUsername());
            ps.executeQuery();
            if (rs.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }
    /**
     * 把user数据加入到数据库中
     * @param user
     */
    public static void addDatebase(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=JDBCUtils.getConnection();
            ps=conn.prepareStatement("insert  into user values (null,?,?,?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(1,user.getNickname());
            ps.setString(1,user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }

    public User checkUsernameAndPassword(String username, String password) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=JDBCUtils.getConnection();
            ps=conn.prepareStatement("select *from  user  where username=? and password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if (rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setUsername(rs.getString("password"));
                user.setUsername(rs.getString("nickname"));
                user.setUsername(rs.getString("email"));
                return  user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }
}
