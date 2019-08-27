package test;

import cn.db.jdbc.PropertiesDemo;

public class test {

    public static void main(String[] args) {
        PropertiesDemo.init();

        //修改
        PropertiesDemo.update("password","123456");
        System.out.println(PropertiesDemo.get("password"));

        //删除
        PropertiesDemo.delete("username");
        System.out.println(PropertiesDemo.get("username"));

        //获取所有
        PropertiesDemo.list();
    }

}
