# 进入公司第一个任务

## 2019-08-20 开始做

将树型数据表转换成JSON（封装成一个方法）。

封装性在Java当中的体现：（来自C:\Users\hhqiwei\Documents\学习\(更新)黑马IDEA版本2018Java基础+就业课程\1.基础班\1-2 面向对象和封装\第1节 描述类介绍与使用\day06_13_面向对象三大特征之封装性.mp4）
1.方法就是一种封装。
2.关键字private也是一种封装。

封装就是讲一些细节信息隐藏起来，对于外界不可见。

### 17:49

[java list 转树 tree 的三种写法](https://blog.csdn.net/jcroad/article/details/79735790)

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
[如何找到IDEA连接mysql数据库的jar包本地路径](https://jingyan.baidu.com/article/948f592474f2c7980ef5f97c.html)

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

使用硬编码初始化Druid，成功连接到数据库，接下来就是要传入参数修改就行。参考：[数据库连接池之Druid连接池(mysql)](https://blog.csdn.net/Dear_UU/article/details/8937049)

### 15:09

重新编写JDBCUtils类的getConnection()方法，用传入参数的方法解决了修改配置文件但是无法及时更新的问题。

### 16:04

通过连接池连接ORACLE数据库，一开始出现“八月 27, 2019 4:03:56 下午 com.alibaba.druid.pool.DruidAbstractDataSource error
严重: oracle.jdbc.driver.OracleDriver is deprecated.”错误。
解决方法参考：Spring boot项目启动报错oracle.jdbc.driver.OracleDriver is deprecated，但项目还可以起来<https://blog.csdn.net/yanyanhj/article/details/88352908>
将driverClassName="oracle.jdbc.driver.OracleDriver";改为driverClassName="oracle.jdbc.OracleDriver";

### 18:01

自动生成MYSQL数据表测试数据。参考：[使用navicat premium 批量生成测试数据](https://blog.csdn.net/yxw_android/article/details/80817174)

### 18:26

将生成的JSON数据输出到文件中（未完成）。

### 18:28

将treetable.sql保存到项目中。

## 2019-08-28 星期三

### 09:35

完成将将生成的JSON数据输出到文件中。

### 10:23

发现自动生成的数据库数据不符合树型结构，自己的ID为7，PID也为7，这是不符合要求的。

### 11:41

虽然解决了上一个问题，但是发现自动生成的数据会形成闭环，导致生成的JSON丢失（暂时无法解决）。

### 17:44

与导师交流发现我自己生成的数据并不符合他的要求，复杂程度不够，会造成程序的运行时间减少。应该让每个数据都有父节点，而不是随机赋值。

### 17:59

增加test.sql文件。

## 2019-08-29 星期四

### 11:17

把以前的项目迁移到maven。

### 14:50

添加单元测试Junit，并编写测试用例：ConnectSQLTest()。

### 15:12

当数据有10000条的时候，运行程序出现“java.lang.StackOverflowError”错误，说明递归方法不适合在大量数据时使用。

### 18:58

下班上传保存。无法将递归转换为循环。

## 2019-08-30 星期五

### 16:29

使用其他方法构造父子关系的JSON，但是并不能解决栈溢出。参考：[java将list转为树形结构的方法](https://www.cnblogs.com/zhizhao/p/9956158.html)

### 17:47

使用@SuppressWarnings("all")压制警告信息。

## 2019-09-02 星期一

### 18:08:20

下班保存。发现了问题：当数据量非常大的时候，TOJSONSTRING()方法会循环调用，导致栈溢出StackOverflow异常。使用别的方法将list转为JSON数据。

## 2019-09-03 星期二

### 15:22:56

由treelist输出整个链表。

### 16:37:56

导师的意思是让我自己写将list转为JSON的方法，对我直接使用网上架包的JSON.toJSONString(treeList)不是很满意，
现在布置给我新的任务：熟悉Redis。以后再来完善这个项目。

### 16:42:30

导师问我的问题：什么是栈比，什么是栈溢出。

## 2019-10-08 星期二

### 11:49:34

导师要求完善，修改以运行。

### 17:33:07

精简代码。

### 18:33:42

下班回家。

## 2019-10-09 星期三

### 11:58:23

多层嵌套使栈溢出，暂时无法解决,无论使用哪个架包，都是会栈溢出，JSON.toJSONString(this)，询问了李子健、何家建，都无法解决。

### 14:28:01

精简代码。

## 2019-10-28 星期一

### 9:49:21

在ubuntu中安装postgresql数据库，并通过Navicat连接成功。
账号：psostgres，密码；123456

### 14:34:27

在Win上安装Oracle数据库，并通过Navicat连接成功。
在ubuntu中安装mongodb数据库，并通过Navicat连接成功。

### 15:57:06

项目连接ORACLE XEE 11G成功。

### 17:31:48

连接postgresql、sqlite失败。

## 2019-11-04 星期一

### 17:43:38

成功连接sqlite数据库，并解决出现的错误“testWhileIdle is true, validationQuery not set”。参考：
[严重: testWhileIdle is true, validationQuery not set 使用Druid连接池报错处理【无Spring框架，使用的JDK9，MYSQL8版本】](https://blog.csdn.net/weixin_42323802/article/details/82726267)

## 2019-11-05 星期二

### 9:01:53

成功连接postgresql数据库。

### 10:26:47

完善ConnectSQL类的编写，并测试各个数据库读取出来的数据转换。问题：Oracle数据库失败。

### 11:35:03

解决不能连接Oracle数据库的问题。参考：[druid配置oracle遇到: 未找到要求的 FROM 关键字 errorCode 923, state 42000](https://www.cnblogs.com/yueguanguanyun/p/9295145.html)

### 11:37:47

连接sqlite出现：testWhileIdle is true, validationQuery not set。参考：[严重: testWhileIdle is true, validationQuery not set 使用Druid连接池报错处理【无Spring框架，使用的JDK9，MYSQL8版本】](https://blog.csdn.net/weixin_42323802/article/details/82726267)

## 2019-11-11 星期一

### 14:44:19

删除一些不必要的代码。今天不要在公司修改，回到家，对代码进行修改。