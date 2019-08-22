package cn.db.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
//    public enum DBType {
//        MySQL,
//        Oracle
//    }
//
//    private JDBCUtils(){}
//
//    public static DataSource getDataSource(DBType dbType){
//        DataSource ds=null;
//
//        if(dbType==DBType.Oracle){
//
//        }else if(dbType==DBType.MySQL){
//            dataSource=
//        }
//        return dataSource;
//    }

    //利用传入的参数选择对应的连接方式以连接不同的数据库
    //dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
    ResultSet chooseDB(String dbType, String dbName, String tableName, String user, String password) {
        if (dbType == "MySQL") {
            return ConMySQL(dbName, tableName, user, password);
        } else if (dbType == "Oracle") {
            return ConOracle(dbName, tableName, user, password);
        } else return null;
    }

    private ResultSet ConMySQL(String dbName, String tableName, String user, String password) {
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=GMT%2B8";
//		String user = "root";
//		String password = "123456";

//        String driver = null;
//        String url = null;
//        String user = null;
//        String password = null;
        ResultSet res = null;

//        String profilepath = "src/util/jdbc.properties";
//        Properties pro = new Properties();
//        try {
//            pro.load(new FileInputStream(profilepath));
//            driver = pro.getProperty("driverClass");
//            url = pro.getProperty("url");
//            user = pro.getProperty("user");
//            password = pro.getProperty("password");
//
//            System.out.println(pro.getProperty("url"));
//            System.out.println(pro.getProperty("user"));
//            System.out.println(pro.getProperty("password"));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        } catch (IOException e) {
//            System.exit(-1);
//        }


        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeed connecting to the Database!");
            }
            Statement statement = con.createStatement();
            String sql = "select * from " + tableName;
            ResultSet rs = statement.executeQuery(sql);

            int node_id = 0;
            String name = null;
            int parent_id = 0;

//            List<Tree> list = new ArrayList<Tree>();
            res = rs;
            while (rs.next()) {
                node_id = rs.getInt("id");
                name = rs.getString("name");
                parent_id = rs.getInt("pid");
                System.out.println(node_id + "\t" + name + "\t" + parent_id);

//                list.add(new Tree(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

//            ToJson tj = new ToJson();
//            tj.treeToJson(list);//调用函数，传入List<Tree>参数

            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can't find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("end!");
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

            while (res.next()) {
                System.out.println(res.getString("id"));
                System.out.println(res.getString("name"));
                System.out.println(res.getString("age"));
                System.out.println(res.getString("pid"));
            }
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
