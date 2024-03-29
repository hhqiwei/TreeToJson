package com.ccmc.jdbc;

import java.io.IOException;
import java.util.Scanner;

public class TreeToJson {

    public static void main(String[] args) {

        String DBType;
        String dbName;
        String tableName;
        String user;
        String password;

        //用户输入数据
        Scanner sc = new Scanner(System.in);

        //数据库类型:mysql、oracle
        System.out.print("Please input the DBType:");
        DBType = sc.nextLine();

        //数据库名称
        System.out.print("Please input the dbName:");
        dbName = sc.nextLine();

        //数据表名称
        System.out.print("Please input the tableName:");
        tableName = sc.nextLine();

        //用户名字
        System.out.print("Please input the user:");
        user = sc.nextLine();

        //用户密码
        System.out.print("Please input the password:");
        password = sc.nextLine();

        long startTime = System.currentTimeMillis(); // 获取开始时间
        //调用数据库方法返回查找到的数据库内容
        try {
            ConnectSQL.chooseDB((String) DBType, (String) dbName, (String) tableName, (String) user, (String) password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }
}
