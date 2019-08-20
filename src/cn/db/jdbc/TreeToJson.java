/**
 * 2019-08-20-��  09:48
 * ������
 * ��MYSQL�е��������ݱ�ת����JSON��ʽ��ʹ��GSON�ܰ���
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

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.sql.CallableStatement;

public class TreeToJson {

	public static void main(String[] args) {

//		TreeToJson tt = new TreeToJson();
//		tt.GetSQL();

//		ChildrenAndParent cap=new ChildrenAndParent();
//		cap.setId(1);
//		cap.setName("a");
//		cap.setChildrenId(12);
//		cap.spark();

		List<Tree> list = new ArrayList<Tree>();
		list.add(new Tree(1, "FOOD", 0));
		list.add(new Tree(2, "FRUIT", 1));
		list.add(new Tree(3, "RED", 2));
		list.add(new Tree(4, "CHERRY", 3));
		list.add(new Tree(5, "YELLOW", 2));
		list.add(new Tree(6, "BANANA", 5));
		list.add(new Tree(7, "MEAT", 1));
		list.add(new Tree(8, "BEEF", 7));
		list.add(new Tree(9, "PORK", 7));

		List<Tree> treeList = new ArrayList<Tree>();
		List<Tree> treeList1 = new ArrayList<Tree>();
		List<Tree> treeList2 = new ArrayList<Tree>();
		List<Tree> treeList3 = new ArrayList<Tree>();

		treeList = listGetStree(list);
		treeList1 = listToTree(list);
		treeList2 = toTree(list);

//		System.out.println(JSON.toJSONString(treeList));
        System.out.println(JSON.toJSONString(treeList1));
//        System.out.println(JSON.toJSONString(treeList2));
	}

	private static List<Tree> listGetStree(List<Tree> list) {
		List<Tree> treeList = new ArrayList<Tree>();
		for (Tree tree : list) {
			// 找到根
			if (tree.getPid() == 0) {
				treeList.add(tree);
			}
			// 找到子
			for (Tree Tree : list) {
				if (Tree.getPid() == tree.getId()) {
					if (tree.getChildren() == null) {
						tree.setChildren(new ArrayList<Tree>());
					}
					tree.getChildren().add(Tree);
				}
			}
		}
		return treeList;
	}

	public static List<Tree> listToTree(List<Tree> list) {
		// 用递归找子。
		List<Tree> treeList = new ArrayList<Tree>();
		for (Tree tree : list) {
			if (tree.getPid() == 0) {
				treeList.add(findChildren(tree, list));
			}
		}
		return treeList;
	}

	private static Tree findChildren(Tree tree, List<Tree> list) {
		for (Tree node : list) {
			if (node.getPid() == tree.getId()) {
				if (tree.getChildren() == null) {
					tree.setChildren(new ArrayList<Tree>());
				}
				tree.getChildren().add(findChildren(node, list));
			}
		}
		return tree;
	}

	private static List<Tree> toTree(List<Tree> list) {
		List<Tree> treeList = new ArrayList<Tree>();
		for (Tree tree : list) {
			if (tree.getPid() == 0) {
				treeList.add(tree);
			}
		}
		for (Tree tree : list) {
			toTreeChildren(treeList, tree);
		}
		return treeList;
	}

	/**
	 * @param treeList
	 * @param tree
	 */
	private static void toTreeChildren(List<Tree> treeList, Tree tree) {
		for (Tree node : treeList) {
			if (tree.getPid() == node.getId()) {
				if (node.getChildren() == null) {
					node.setChildren(new ArrayList<Tree>());
				}
				node.getChildren().add(tree);
			}
			if (node.getChildren() != null) {
				toTreeChildren(node.getChildren(), tree);
			}
		}
	}

	// 连接数据库
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
				Map<String, String> map = new HashMap<String, String>();
				map.put("node_id", rs.getString(1));
				map.put("name", rs.getString(2));
				map.put("parent_id", rs.getString(3));
				list.add(map);
				node_id = rs.getInt("id");
				name = rs.getString("name");
				parent_id = rs.getInt("pid");
				System.out.println(node_id + "\t" + name + "\t" + parent_id);
			}

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
