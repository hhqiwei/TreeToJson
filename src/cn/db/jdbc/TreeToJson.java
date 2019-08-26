/**
 * 2019-08-20-��  09:48
 * ������
 * ��MYSQL�е��������ݱ�ת����JSON��ʽ��ʹ��GSON�ܰ���
 */
//mysql:"MySQL", "test", "treetable", "root", "123456"
//oracle:"Oracle", "XE", "test", "c##hhqiwei", "123456"
package cn.db.jdbc;

import java.sql.ResultSet;
import java.util.Scanner;

public class TreeToJson {

    public static void main(String[] args) {

        ConnectSQL utils = new ConnectSQL();

        String DBType;
        String dbName;
        String tableName;
        String user;
        String password;

        //用户输入数据
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input thd DBType:");
        DBType = sc.nextLine();

        System.out.print("Please input thd dbName:");
        dbName = sc.nextLine();

        System.out.print("Please input thd tableName:");
        tableName = sc.nextLine();

        System.out.print("Please input thd user:");
        user = sc.nextLine();

        System.out.print("Please input thd password:");
        password = sc.nextLine();

        System.out.println(DBType + '\t' + dbName + '\t' + tableName + '\t' + user + '\t' + password);

        //调用数据库方法返回查找到的数据库内容
        ResultSet rs = utils.chooseDB((String) DBType, (String) dbName, (String) tableName, (String) user, (String) password);
//        System.out.println("==============================");
//        utils.chooseDB("Oracle", "XE", "test", "c##hhqiwei", "123456");


//		List<Tree> list = new ArrayList<Tree>();
//		list.add(new Tree(1, "FOOD", 0));
//		list.add(new Tree(2, "FRUIT", 1));
//		list.add(new Tree(3, "RED", 2));
//		list.add(new Tree(4, "CHERRY", 3));
//		list.add(new Tree(5, "YELLOW", 2));
//		list.add(new Tree(6, "BANANA", 5));
//		list.add(new Tree(7, "MEAT", 1));
//		list.add(new Tree(8, "BEEF", 7));
//		list.add(new Tree(9, "PORK", 7));
//
//		List<Tree> treeList = new ArrayList<Tree>();
//		List<Tree> treeList1 = new ArrayList<Tree>();
//
//		treeList1 = listToTree(list);
//
//		System.out.println(JSON.toJSONString(treeList));
//		System.out.println(JSON.toJSONString(treeList1));
    }

    // 连接mysql数据库
//    public static void ConMySQL() {
//        Connection con;
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String url = "jdbc:mysql://localhost:3306/test" + "?serverTimezone=GMT%2B8";
//		String user = "root";
//		String password = "123456";
//
//        String driver = null;
//        String url = null;
//        String user = null;
//        String password = null;
//
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
//
//
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, user, password);
//            if (!con.isClosed()) {
//                System.out.println("Succeed connecting to the Database!");
//            }
//            Statement statement = con.createStatement();
//            String sql = "select * from treetable";
//            ResultSet rs = statement.executeQuery(sql);
//
//            int node_id = 0;
//            String name = null;
//            int parent_id = 0;
//
//            List<Tree> list = new ArrayList<Tree>();
//            while (rs.next()) {
//                node_id = rs.getInt("id");
//                name = rs.getString("name");
//                parent_id = rs.getInt("pid");
//                System.out.println(node_id + "\t" + name + "\t" + parent_id);
//
//                list.add(new Tree(rs.getInt(1), rs.getString(2), rs.getInt(3)));
//            }
//
//            ToJson tj = new ToJson();
//            tj.treeToJson(list);//调用函数，传入List<Tree>参数
//
//            rs.close();
//            con.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Sorry,can't find the Driver!");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("end!");
//        }
//    }

}
