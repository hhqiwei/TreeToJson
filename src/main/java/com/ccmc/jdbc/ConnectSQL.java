package com.ccmc.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.SourceDataLine;

/**
 * @author hhqiw
 */
public class ConnectSQL {
    // 利用传入的参数选择对应的连接方式以连接不同的数据库

    /**
     * dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
     *
     * @param dbType
     * @param dbName
     * @param tableName
     * @param user
     * @param password
     * @throws IOException
     */
    public static void chooseDB(String dbType, String dbName, String tableName, String user, String password)
            throws IOException {

        if ("mysql".equals(dbType.toLowerCase())) {
            System.out.println("Connecting the MySQL,please wait!");
            ConnectionMySQL(dbName, tableName, user, password);
        } else if ("oracle".equals(dbType.toLowerCase())) {
            System.out.println("Connecting the Oracle,please wait!");
            ConnectionOracle(dbName, tableName, user, password);
        } else if ("postgresql".equals(dbType.toLowerCase())) {
            System.out.println("Connecting the PostgreSQL,please wait!");
            ConnectionPostgreSQL(dbName, tableName, user, password);
        } else if ("sqlite".equals(dbType.toLowerCase())) {
            System.out.println("Connecting the SQLite,please wait!");
            ConnectionSQLite(dbName, tableName, user, password);
        }
    }

    // 连接MYSQL数据库
    private static void ConnectionMySQL(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        /**
         * 四个基本属性 ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         * ds.setUrl("jdbc:mysql://localhost:3306/huangqiwei?serverTimezone=GMT%2B8");
         * ds.setUsername("root"); ds.setPassword("123456");
         */
        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=GMT%2B8";
            // 调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);
            String sql = String.format("SELECT * FROM %s", tableName.toUpperCase());
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();

            while (res.next()) {
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
            }
            try {
                // 调用函数，传入List<Tree>参数
                new ToJson().treeToJson(list);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Success let trees to json!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    /**
     * 连接ORACLE数据库
     * 
     * @param dbName
     * @param tableName
     * @param user
     * @param password
     */
    private static void ConnectionOracle(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        /*
         * 四个基本属性 ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         * ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
         * ds.setUsername("root"); ds.setPassword("root");
         */
        // jdbc:oracle:thin:@127.0.0.1:1521:ORCL
        System.out.println("tableNmae is :" + tableName);
        try {
            String driverClassName = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);
            String sql = String.format("SELECT * FROM %s", tableName.toUpperCase());
            pstm = conn.prepareStatement(sql);
            res = pstm.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
                // System.out.println(res.getString("id") + '\t' + res.getString("name") + '\t'
                // +
                // res.getString("age") + '\t' + res.getString("pid"));
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(4)));
            }
            ToJson tj = new ToJson();
            // 调用函数，传入List<Tree>参数
            tj.treeToJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstm, conn);
        }
    }

    /**
     * 连接SQLite数据库 2019-11-05 星期二 9:51:36
     */
    public static void ConnectionSQLite(String dbUrl, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
            String driverClassName = "org.sqlite.JDBC";
            String url = "jdbc:sqlite:" + dbUrl;
            // 调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, "", "");
            String sql = String.format("SELECT * FROM %s", tableName);
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
            }
            ToJson tj = new ToJson();
            tj.treeToJson(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    /**
     * 连接PostgreSQL数据库 2019-11-05 星期二 9:51:28
     */
    public static void ConnectionPostgreSQL(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
            String driverClassName = "org.postgresql.Driver";
            String url = "jdbc:postgresql://192.168.57.131:5432/" + dbName;
            // 调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);
            String sql = String.format("SELECT * FROM %s", tableName);
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2) + "\t" + res.getInt(3));
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
            }
            try {
                // 调用函数，传入List<Tree>参数
                new ToJson().treeToJson(list);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Success let trees to json!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }
}
