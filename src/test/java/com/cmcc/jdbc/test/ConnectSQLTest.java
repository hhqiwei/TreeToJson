package com.cmcc.jdbc.test;

import com.ccmc.jdbc.ConnectSQL;
import org.junit.Test;

import java.io.IOException;

public class ConnectSQLTest {
    @Test
    public void testConnectSQL() {
        System.out.println("test!");
    }

    // 测试连接MySQL数据库,基本连接测试
    @Test
    public void testConMySQL() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "treetable", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接MySQL数据库,city_100
    @Test
    public void testConMySQLFor100() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_100", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接MySQL数据库,city_1000
    @Test
    public void testConMySQLFor1000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_1000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接MySQL数据库,city_10000
    @Test
    public void testConMySQLFor10000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_10000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接MySQL数据库,city_100000
    @Test
    public void testConMySQLFor100000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_100000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接Oracle数据库,基本测试数据
    @Test
    public void testConOracle() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("oracle", "HUANGQIWEI", "CITY_100", "HUANGQIWEI", "HuangQiwei123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试连接postgresql，基本数据测试
    @Test
    public void testPostgreSQL() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("postgresql", "huangqiwei", "city_100", "postgres", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 测试连接SQLite，基本数据测试
    @Test
    public void testSQLite(){
        ConnectSQL cs= new ConnectSQL();
        try {
        cs.chooseDB("sqlite", "C:\\Users\\hhqiw\\Documents\\DataBase\\SQLite3\\Data\\huangqiwei.db", "city_100", "", "");    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
