package com.ccmc.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsTest {
    /**
     * 测试连接MySQL数据库
     * 2019-10-28 星期一 15:19:33
     */
    @Test
    public void getConnectionMySql() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        /**
         *  四个基本属性
         *  ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         *  ds.setUrl("jdbc:mysql://localhost:3306/huangqiwei?serverTimezone=GMT%2B8");
         *  ds.setUsername("root");
         *  ds.setPassword("123456");
         */
        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + "huangqiwei" + "?serverTimezone=GMT%2B8";
            //调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, "root", "123456");
            String sql = String.format("SELECT * FROM %s", "city_100");
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }


    /**
     * 测试连接Oracle数据库
     * 2019-10-28 星期一 15:20:00
     */
    @Test
    public void getConnectionOracle() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        /**
         *  四个基本属性
         *  ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         *  ds.setUrl("jdbc:mysql://localhost:3306/huangqiwei?serverTimezone=GMT%2B8");
         *  ds.setUsername("root");
         *  ds.setPassword("123456");
         */
        try {
            String driverClassName = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            //调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, "HUANGQIWEI", "HuangQiwei123");
            String sql = String.format("SELECT * FROM %s", "city_100");
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    /**
     * 测试连接PostgresSQL数据库
     * 2019-10-28 星期一 15:20:00
     */
    @Test
    public void getConnectionPostgresSQL() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        /**
         *  四个基本属性
         *  ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         *  ds.setUrl("jdbc:mysql://localhost:3306/huangqiwei?serverTimezone=GMT%2B8");
         *  ds.setUsername("root");
         *  ds.setPassword("123456");
         */
        try {
            String driverClassName = "org.postgresql.Driver";
            String url = "jdbc:postgresql://192.168.57.131:5432/huangqiwei?currentSchema=jfinal";
            //调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, "postgres", "123456");
            String sql = String.format("SELECT t FROM %s", "city_100");
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }


    /**
     * 测试连接SQLite数据库
     * 2019-10-28 星期一 15:20:00
     */
    @Test
    public void getConnectionSQLite() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        /**
         *  四个基本属性
         *  ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         *  ds.setUrl("jdbc:mysql://localhost:3306/huangqiwei?serverTimezone=GMT%2B8");
         *  ds.setUsername("root");
         *  ds.setPassword("123456");
         */
        try {
            String driverClassName = "org.sqlite.JDBC";
            String url = "jdbc:sqlite:C:/Users/hhqiw/Documents/DataBase/SQLite3/Data/huangqiwei.db";
            //调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, "","" );
            String sql = String.format("SELECT t FROM %s", "city_100");
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }
}
