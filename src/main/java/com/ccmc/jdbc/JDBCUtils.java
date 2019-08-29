package com.ccmc.jdbc;


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    //1.定义成员变量 DruidDataSource
    public static DruidDataSource ds;

    //获取连接，用传入参数的方法解决了修改配置文件但是无法及时更新的问题。
    public static Connection getConnection(String driverClassName,String url,String userName,String password) throws SQLException {
        //硬编码初始化Druid连接池
        try {
            ds = new DruidDataSource();
            //四个基本属性
            ds.setDriverClassName(driverClassName);
            ds.setUrl(url);
            ds.setUsername(userName);
            ds.setPassword(password);
            //其他属性
            //初始大小
            ds.setInitialSize(10);
            //最大大小
            ds.setMaxActive(50);
            //最小大小
            ds.setMinIdle(10);
            //检查时间
            ds.setMaxWait(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds.getConnection();
    }

    //释放资源
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    //释放资源,重载
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取连接池方法
    public static DataSource getDataSource() {
        return ds;
    }
}
