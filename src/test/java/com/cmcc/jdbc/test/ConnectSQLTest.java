package com.cmcc.jdbc.test;

import com.ccmc.jdbc.ConnectSQL;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;

@SuppressWarnings("all")
public class ConnectSQLTest {
    @Test
    public void testConnectSQL() {
        System.out.println("test!");
    }

    //测试连接MySQL数据库,基本连接测试
    @Test
    public void testConMySQL(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("mysql","test","treetable","root","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_100
    @Test
    public void testConMySQLFor100(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("mysql","test","city_100","root","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_1000
    @Test
    public void testConMySQLFor1000(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("mysql","test","city_1000","root","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_10000
    @Test
    public void testConMySQLFor10000(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("mysql","test","city_10000","root","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_100000
    @Test
    public void testConMySQLFor100000(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("mysql","test","city_100000","root","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接Oracle数据库,基本测试数据
    @Test
    public void testConOracle(){
        ConnectSQL cs=new ConnectSQL();
        try {
            cs.chooseDB("oracle","test","test","c##hhqiwei","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
