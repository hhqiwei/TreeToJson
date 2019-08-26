package cn.db.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

    //1.定义成员变量 DataSource
    private static DataSource ds;

    static {

        try {//1.加载配置文件
            Properties pro=new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取DataSource
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //释放资源
    public static void close(Statement stmt,Connection conn){
//        if(stmt!=null){
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(conn!=null){
//            try {
//                conn.close();//归还连接
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        close(null,stmt,conn);
    }

    //释放资源,重载
    public static void close(ResultSet rs,Statement stmt,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //获取连接池方法
    public  static DataSource getDataSource(){
        return ds;
    }

    //利用传入的参数选择对应的连接方式以连接不同的数据库
    //dbType:数据库类型;dbName:数据库名;tableName:表名;user:用户名;password:密码;
    ResultSet chooseDB(String dbType, String dbName, String tableName, String user, String password) {
        if (dbType.equals("MySQL")) {
            System.out.println("Connecting the MySQL,please wait!");
            return ConMySQL(dbName, tableName, user, password);
        } else if (dbType.equals("Oracle")) {
            System.out.println("Connecting the Oracle,please wait!");
            return ConOracle(dbName, tableName, user, password);
        } else return null;
    }

    //连接MYSQL数据库
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

        //连接配置文件
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

            List<Tree> list = new ArrayList<Tree>();
            res = rs;
            while (rs.next()) {
                node_id = rs.getInt("id");
                name = rs.getString("name");
                parent_id = rs.getInt("pid");
                System.out.println(node_id + "\t" + name + "\t" + parent_id);

                list.add(new Tree(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

            ToJson tj = new ToJson();
            tj.treeToJson(list);//调用函数，传入List<Tree>参数

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
