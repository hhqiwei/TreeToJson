package druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class DruidDemo {
    public static void main(String[] args){

        Properties pro=new Properties();
        InputStream is=DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSource ds= null;
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn= null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(conn);




    }
}
