package org.example.dao;

import org.example.model.User;
import org.example.Util.DBUtil;
import org.example.exception.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class loginDAO {

    public static User query(String username) {
        Connection c = null;
        PreparedStatement ps =null;
        ResultSet rs = null;


        try{
            c = DBUtil.getConnection();
            String sql = "select id,username,password,nickname,sex,birthday,head from user where username = ?";
            ps = c.prepareStatement(sql);

            //设置占位符
            ps.setString(1,username);

            rs = ps.executeQuery();
            User user = null;

            while (rs.next()) {
                user = new User() ;
                //设置User的值
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setSex(rs.getBoolean("sex"));
               java.sql.Date birthday=rs.getDate("birthday");
                // rs.getDate();,java中一般使用java.util.Date,表示（年月日时分秒）
                if (birthday!=null) {
                    user.setBirthday(new Date(birthday.getTime()));

                    user.setHead(rs.getString("head"));
                }

            }
            return user;

        }catch (Exception e){
            throw new AppException("LOG001","查询用户操作出错",e);
        }finally {
            DBUtil.close(c,ps,rs); //释放资源
        }

    }
}
