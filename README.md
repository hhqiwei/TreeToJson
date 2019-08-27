## 2019-08-20 开始做
将树型数据表转换成JSON（封装成一个方法）。

封装性在Java当中的体现：（来自C:\Users\hhqiwei\Documents\学习\(更新)黑马IDEA版本2018Java基础+就业课程\1.基础班\1-2 面向对象和封装\第1节 描述类介绍与使用\day06_13_面向对象三大特征之封装性.mp4）
1.方法就是一种封装。
2.关键字private也是一种封装。

封装就是讲一些细节信息隐藏起来，对于外界不可见。
### 17:49 
java list 转树 tree 的三种写法(https://blog.csdn.net/jcroad/article/details/79735790)

## 2019-08-21
导师意见：
1、不够通用性。
2、可以连接MySQL、Oracle等不同的数据库。
3、数据库账号密码等不能写死，因为每个数据库的账号密码都不同。
4、由数据表找出父子关系，不能写死。（现在我就是由我自己定义的父子关系表，所以很简单的就可以找出来了）

## 2019-08-22 
### 10:34 
添加配置文件并读取。
### 16:18
建立Oracle数据库并且读取数据表内容。
（如何找到IDEA连接mysql数据库的jar包本地路径）[https://jingyan.baidu.com/article/948f592474f2c7980ef5f97c.html]
### 17:34
成功将连接数据库的方法封装成一个类，并成功调用。
### 18:23
由用户输入的数据来调用方法。（但是调用方法的时候，参数传进去了，还是调用调用不成功，可能是传入的数据类型不对，接下来检查数据类型）

## 2019-08-23 
### 09:10
解决昨天下午遇到的传参不正确问题，不是用“==”进行字符串的比较，而是是用“str.equals("str1")”。
### 10:18
完成从ORACLE数据库中读取数据并转换为JSON，但是还没有实现自动识别父子关系的方法。
### 16：42
导师意见：
1.一万条数据的话，又会怎么样？（耗时多少？所以会有算法优化）
2.连接数据库的考虑。（连接池的设计）
3.有很多坑，尽量优化，发现问题。
4.导师说父子关系不一定是影响性能的重点。（说明不用从数据库中判断父子关系）
5.传入的参数尽可能的简便，方便用户使用。

综上，优化代码，提升性能。

## 2019-08-26 
### 14:42
经过几天的考虑，计划使用数据库连接池连接数据库。DRUID连接池技术，由阿里巴巴提供。
### 15:42
成功使用DRUID连接池技术连接数据库。
### 16:34
编写JDBCUtils类，并测试。
### 17:19
分离连接池和数据库连接，分别为：JDBCUtils、ConnectSQL。
### 21:11
尝试通过连接池test数据库,因为家里的电脑没有MYSQL，暂时无法连接上。

## 2019-08-27 星期二 
### 08:55
1.删除一些多余的代码。
2.成功通过连接池操作MySQL数据库，并成功显示出JSON数据。
### 10:30
通过新建类PropertiesDemo来修改配置文件druid.properties中的数据。（出现了一个问题，虽然修改成功了，但是并不能及时读取到修改以后的数据，而是读取修改之前的
数据，说明数据更新不及时）
### 14:50
使用硬编码初始化Druid，成功连接到数据库，接下来就是要传入参数修改就行。参考：数据库连接池之Druid连接池(mysql)[https://blog.csdn.net/Dear_UU/article/details/89370491]
### 15:09
重新编写JDBCUtils类的getConnection()方法，用传入参数的方法解决了修改配置文件但是无法及时更新的问题。
### 16:04
通过连接池连接ORACLE数据库，一开始出现“八月 27, 2019 4:03:56 下午 com.alibaba.druid.pool.DruidAbstractDataSource error 
严重: oracle.jdbc.driver.OracleDriver is deprecated.”错误。
解决方法参考：Spring boot项目启动报错oracle.jdbc.driver.OracleDriver is deprecated，但项目还可以起来【https://blog.csdn.net/yanyanhj/article/details/88352908】
将driverClassName="oracle.jdbc.driver.OracleDriver";改为driverClassName="oracle.jdbc.OracleDriver";
### 18:01
自动生成MYSQL数据表测试数据。参考：【使用navicat premium 批量生成测试数据】（https://blog.csdn.net/yxw_android/article/details/80817174）