package com.ccmc.jdbc;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.xdevapi.JsonArray;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ConnectSQL {
    //利用传入的参数选择对应的连接方式以连接不同的数据库
    //dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
    public static void chooseDB(String dbType, String dbName, String tableName, String user, String password) throws IOException {

        if (dbType.equals("mysql")) {
            System.out.println("Connecting the MySQL,please wait!");
            ConMySQL(dbName, tableName, user, password);
        } else if (dbType.equals("oracle")) {
            System.out.println("Connecting the Oracle,please wait!");
            ConOracle(dbName, tableName, user, password);
        }
    }

    //连接MYSQL数据库
    private static void ConMySQL(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int node_id = 0;
        String name = null;
        int parent_id = 0;

        /*
           四个基本属性
           ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
           ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
           ds.setUsername("root");
           ds.setPassword("root");
         */
        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=GMT%2B8";
            conn = JDBCUtils.getConnection(driverClassName, url, user, password);//调用方法，使用连接池
            String sql = String.format("SELECT * FROM %s", tableName.toUpperCase());
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
//            List<Map<String, Object>> data = new ArrayList<>();

            while (res.next()) {
////                Map<String, Object> map = new HashMap<>();
////                node_id = res.getInt("id");
////                name = res.getString("name");
////                parent_id = res.getInt("pid");
////                System.out.println(node_id + "\t" + name + "\t" + parent_id);
////                map.put("id", res.getInt("id"));
////                map.put("name", res.getString("name"));
////                map.put("pid", res.getInt("pid"));
////                data.add(map);
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
            }

//            System.out.println(JSON.toJSONString(list));
            System.out.println("****************************");
            try {
                new ToJson().treeToJson(list);//调用函数，传入List<Tree>参数
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("Success let trees to json!");
            }
            //2019-08-30 星期五 11:17:13 创建新的算法转换父子结构的JOSN
//            System.out.println("################");
//            System.out.println(JSON.toJSONString(data));
//            System.out.println("################");
//            JSONArray result = new listToTree().listToTree(JSONArray.parseArray(JSON.toJSONString(data)), "id", "pid", "children");
//            System.out.println(JSON.toJSONString(result));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    //连接ORACLE数据库
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
        try {
            String driverClassName = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
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
            tj.treeToJson(list);//调用函数，传入List<Tree>参数
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstm, conn);
        }
    }
}
