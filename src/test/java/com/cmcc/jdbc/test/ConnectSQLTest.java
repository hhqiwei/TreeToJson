package com.cmcc.jdbc.test;

import com.ccmc.jdbc.ConnectSQL;
import org.junit.Test;

import java.io.IOException;

public class ConnectSQLTest {
    @Test
    public void testConnectSQL() {
        System.out.println("test!");
    }

    //测试连接MySQL数据库,基本连接测试
    @Test
    public void testConMySQL() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "treetable", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_100
    @Test
    public void testConMySQLFor100() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_100", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_1000
    @Test
    public void testConMySQLFor1000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_1000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_10000
    @Test
    public void testConMySQLFor10000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_10000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接MySQL数据库,city_100000
    @Test
    public void testConMySQLFor100000() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("mysql", "huangqiwei", "city_100000", "root", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试连接Oracle数据库,基本测试数据
    @Test
    public void testConOracle() {
        ConnectSQL cs = new ConnectSQL();
        try {
            cs.chooseDB("oracle", "test", "test", "c##hhqiwei", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cal() {
        for (int a = 1; a < 26500; a++) {
            for (int b = 1; b < 26500; b++) {
                for (int c = 1; c < 26500; c++) {
                    for (int d = 1; d < 26500; d++) {
                        for (int e = 1; e < 26500; e++) {
                            for (int f = 1; f < 26500; f++) {
                                for (int g = 1; g < 26500; g++) {
                                    for (int h = 1; h < 26500; h++) {
                                        if(25*a+28*b+24*c+25*d+10*e+16*f+25*g+25*h==26500){
                                            System.out.println(a+"_"+b+"_"+c+"_"+d+"_"+e+"_"+f+"_"+g+"_"+h);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
