package cn.db.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectSQL {
    //利用传入的参数选择对应的连接方式以连接不同的数据库
    //dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
    public ResultSet chooseDB(String dbType, String dbName, String tableName, String user, String password) throws IOException {


        //尝试更新配置文件
//        try {
//            Thread.sleep(5000);
//            PropertiesDemo pd=new PropertiesDemo();
//            pd.init();
//            pd.update("username", user);//修改用户名
//            pd.update("password", password);
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("Pleae input the right String!");
//        } finally {
//            PropertiesDemo pd=new PropertiesDemo();
//            pd.init();
//            System.out.println("3333333333333333");
//            //获取所有
//            pd.list();
//
//        }

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
            conn = JDBCUtils.getConnection();//调用方法，使用连接池
            String sql = "select * from " + tableName;
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            List<Tree> list = new ArrayList<Tree>();
            while (res.next()) {
                node_id = res.getInt("id");
                name = res.getString("name");
                parent_id = res.getInt("pid");
                System.out.println(node_id + "\t" + name + "\t" + parent_id);

                list.add(new Tree(res.getInt(1), res.getString(2), res.getInt(3)));
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
