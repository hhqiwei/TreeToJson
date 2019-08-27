package cn.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectSQL {
    //利用传入的参数选择对应的连接方式以连接不同的数据库
    //dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
    ResultSet chooseDB(String dbType, String dbName, String tableName, String user, String password) {


        PropertiesDemo.init();
        PropertiesDemo.update("username",user);//修改用户名
        PropertiesDemo.update("password",password);

        if (dbType.equals("mysql")) {
            System.out.println("Connecting the MySQL,please wait!");
            return ConMySQL(dbName, tableName, user, password);
        } else if (dbType.equals("oracle")) {
            System.out.println("Connecting the Oracle,please wait!");
            return ConOracle(dbName, tableName, user, password);
        } else return null;
    }

    //连接MYSQL数据库
    private ResultSet ConMySQL(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        int node_id = 0;
        String name = null;
        int parent_id = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from "+tableName;
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (rs.next()) {
                node_id = rs.getInt("id");
                name = rs.getString("name");
                parent_id = rs.getInt("pid");
                System.out.println(node_id + "\t" + name + "\t" + parent_id);

                list.add(new Tree(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            ToJson tj = new ToJson();
            tj.treeToJson(list);//调用函数，传入List<Tree>参数
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
        return res;
    }


    //连接ORACLE数据库
    public ResultSet ConOracle(String dbName, String tableName, String user, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
//            String user = "c##hhqiwei";
//            String password="123456";
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from " + tableName;
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();

            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
                System.out.println(res.getString("id") + '\t' + res.getString("name") + '\t' +
                        res.getString("age") + '\t' + res.getString("pid"));
                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(4)));
            }

            ToJson tj = new ToJson();
            tj.treeToJson(list);//调用函数，传入List<Tree>参数

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return res;
    }

}
