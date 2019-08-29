package com.ccmc.jdbc;


import java.io.IOException;
import java.util.Scanner;

public class TreeToJson {

    public static void main(String[] args) {
//        ConnectSQL cs = new ConnectSQL();

        String DBType;
        String dbName;
        String tableName;
        String user;
        String password;

        //用户输入数据
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the DBType:");
        DBType = sc.nextLine();

        System.out.print("Please input the dbName:");
        dbName = sc.nextLine();

        System.out.print("Please input the tableName:");
        tableName = sc.nextLine();

        System.out.print("Please input the user:");
        user = sc.nextLine();

        System.out.print("Please input the password:");
        password = sc.nextLine();

//        System.out.println(DBType + '\t' + dbName + '\t' + tableName + '\t' + user + '\t' + password);

        long startTime = System.currentTimeMillis(); // 获取开始时间
        //调用数据库方法返回查找到的数据库内容
        try {
            ConnectSQL.chooseDB((String) DBType, (String) dbName, (String) tableName, (String) user, (String) password);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("==============================");
//        utils.chooseDB("Oracle", "XE", "test", "c##hhqiwei", "123456");

        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

}
