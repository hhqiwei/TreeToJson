/**
 * 2019-08-20-二  09:48
 * 黄启威
 * 將MYSQL中的树型数据表转换成JSON格式（使用GSON架包）
 */
package cn.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import java.sql.CallableStatement;

public class TreeToJson {

	public static void main(String[] args) {
		
		TreeToJson tt=new TreeToJson();
		tt.GetSQL();
		
//		ChildrenAndParent cap=new ChildrenAndParent();
//		cap.setId(1);
//		cap.setName("a");
//		cap.setChildrenId(12);
//		cap.spark();
		
	}
	
	//连接数据库
	public static void GetSQL() {
		Connection con;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test" + "?serverTimezone=GMT%2B8";
		String user = "root";
		String password = "123456";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (!con.isClosed()) {
				System.out.println("Succeed connecting to the Database!");
			}
			Statement statement = con.createStatement();
			String sql = "select * from treetable";
			ResultSet rs = statement.executeQuery(sql);

			int node_id = 0;
			String name = null;
			int parent_id = 0;

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();// 要每次创建一个新的映射表，不然只会保存最后一组数据
				map.put("node_id", rs.getString(1));
				map.put("name", rs.getString(2));
				map.put("parent_id", rs.getString(3));
				list.add(map);
				node_id = rs.getInt("id");
				name = rs.getString("name");
				parent_id = rs.getInt("pid");
				System.out.println(node_id + "\t" + name + "\t" + parent_id);
			}

			// 用GSON中的方法将LIST序列化为JSON字符串
			Gson gson = new Gson();
			String jsonstr = null;
			jsonstr = gson.toJson(list);
			System.out.println(jsonstr);

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
	}

}
