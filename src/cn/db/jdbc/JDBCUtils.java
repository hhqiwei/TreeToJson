package cn.db.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class JDBCUtils {

    //1.定义成员变量 DataSource
//    private static DataSource ds;
    public static DruidDataSource ds;

//    static {
//        try {//1.加载配置文件
//            Properties pro = new Properties();
//            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            //2.获取DataSource
//            ds = DruidDataSourceFactory.createDataSource(pro);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        try {//1.加载配置文件
//            Properties properties = new Properties();
//            //通过类加载器加载配置文件
//            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//            properties.load(inputStream);
//            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            ds = new DruidDataSource();
            //四个基本属性
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
            ds.setUsername("root");
            ds.setPassword("123456");
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
