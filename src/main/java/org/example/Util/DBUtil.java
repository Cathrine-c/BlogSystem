package org.example.Util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/servlet_blog?user=root&password=chen62487&useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    //创建连接池对象
    //private static final DataSource DS = new MysqlDataSource();


    private static DataSource DS;
    private static void DataSource(){};

   //多线程的双重校验锁的单例模式创建DataSource
    public static DataSource getDS(){

            if (DS == null) {

                synchronized (DataSource.class) {

                    if (DS == null) {
                        DS = new MysqlDataSource();
                    }
                }
            }
            return DS;

        }


    /*
    不足：1.static代码块出现错误，NoClassDefFoundError，类可以找到，但类加载失败
   3.数据库ORM框架都是用模板模式来设计的，如Mybatis
     */


    static {

        DataSource Datasource = getDS();

        ((MysqlDataSource) Datasource).setUrl(URL);
    }

    public static Connection getConnection() {
        try {
            return DS.getConnection();
        } catch (SQLException e) {
            throw new AppException("DB001", "获取数据库连接失败", e);

        }
    }


   //自定义释放资源
    public static void close(Connection c, Statement s) {
        close(c, s, null);

    }


    public static void close(Connection c, Statement s, ResultSet r) {

        try {
            if (r != null) {
                r.close();
            }
            if (s != null) {
                s.close();
            }
            if (c != null) {

                c.close();
            }
        } catch (SQLException e) {
            throw new AppException("DB002", "数据库释放资源出错", e);
        }

    }

}


