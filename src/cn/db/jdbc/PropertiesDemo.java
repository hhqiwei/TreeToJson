/**
 * 修改配置文件druid.properties中的参数
 * 2019-08-27 星期二 10:16
 */
package cn.db.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesDemo {

    public static final Properties p = new Properties();
    public static final String path = "./src/druid.properties";

    /**
     * 通过类装载器 初始化Properties
     */
    public static void init() {
        //转换成流
        InputStream inputStream =
//                PropertiesDemo.class.getClassLoader().getResourceAsStream(path);
                PropertiesDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            //从输入流中读取属性列表（键和元素对）
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key获取value
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return p.getProperty(key);
    }

    /**
     * 修改或者新增key
     *
     * @param key
     * @param value
     */
    public static void update(String key, String value) {
        p.setProperty(key, value);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            //将Properties中的属性列表（键和元素对）写入输出流
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过key删除value
     *
     * @param key
     */
    public static void delete(String key) {
        p.remove(key);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 循环所有key value
     */
    public static void list() {
        Enumeration en = p.propertyNames(); //得到配置文件的名字
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = p.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }
}
