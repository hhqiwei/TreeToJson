/**
 * 测试DRUID连接池，2019年8月26日 15:59
 */
package druid;

import cn.db.jdbc.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class DruidDemo {
    //测试DRUID连接池，2019年8月26日 15:59
//    public static void main(String[] args){
//
//        Properties pro=new Properties();
//        InputStream is=DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
//
//        try {
//            pro.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        DataSource ds= null;
//        try {
//            ds = DruidDataSourceFactory.createDataSource(pro);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Connection conn= null;
//        try {
//            conn = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(conn);
//    }

    //2019年8月26日 16:11 测试工具类JDBCUtils，完成添加操作，给test表添加一条记录
    public static void main(String[] args) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            //        1.获取连接
//            conn = JDBCUtils.getConnection();
//            //        2. 定义sql
//            String sql = "insert into treetable values(null,?,?)";
////        3. 获取pstm对象
//            pstmt = conn.prepareStatement(sql);
////        4. 给？赋值
//            pstmt.setString(1, "黄启威");
//            pstmt.setInt(2, 0);
////        5. 执行sql
//            int count = pstmt.executeUpdate();
//            System.out.println(count);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
////            6.释放资源
//            JDBCUtils.close(pstmt, conn);
//        }


        DruidDemo dt= new DruidDemo();
        try {
            dt.DruidTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DruidTest() throws SQLException {
        //超过最大限制或报"TimeoutException",每打开一个关闭一个就不会发生异常
        for (int i = 0; i < 100; i++) {
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection.toString() + "\n------------------------------------");

//            JDBCUtils.closeAll(connection, null, null);
            JDBCUtils.close(null, null, connection);
        }
    }
}
