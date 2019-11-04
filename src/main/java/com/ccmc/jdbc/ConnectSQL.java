package com.ccmc.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hhqiw
 */
public class ConnectSQL {
    //利用传入的参数选择对应的连接方式以连接不同的数据库

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
    public static void chooseDB(String dbType, String dbName, String tableName, String user, String password) throws IOException {

        if ("mysql".equals(dbType)) {
            System.out.println("Connecting the MySQL,please wait!");
            ConMySQL(dbName, tableName, user, password);
        } else if ("oracle".equals(dbType)) {
            System.out.println("Connecting the Oracle,please wait!");
            ConOracle(dbName, tableName, user, password);
        }
    }

    //连接MYSQL数据库
    private static void ConMySQL(String dbName,
                                 String tableName,
                                 String user,
                                 String password) {
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
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=GMT%2B8";
            //调用方法，使用连接池
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);
            String sql = String.format("SELECT * FROM %s", tableName.toUpperCase());
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();

            while (res.next()) {
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
            }
            try {
                //调用函数，传入List<Tree>参数
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
     * @param dbName
     * @param tableName
     * @param user
     * @param password
     */
    private static void ConOracle(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet res = null;

        /*
           四个基本属性
           ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
           ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
           ds.setUsername("root");
           ds.setPassword("root");
         */
//        jdbc:oracle:thin:@127.0.0.1:1521:ORCL
        System.out.println("tableNmae is :"+tableName);
        try {
            String driverClassName = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);
            String sql = String.format("SELECT * FROM %s", tableName.toUpperCase());
            pstm = conn.prepareStatement(sql);
            res = pstm.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
//                System.out.println(res.getString("id") + '\t' + res.getString("name") + '\t' +
//                        res.getString("age") + '\t' + res.getString("pid"));
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(4)));
            }
            ToJson tj = new ToJson();
            //调用函数，传入List<Tree>参数
            tj.treeToJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstm, conn);
        }
    }



}
