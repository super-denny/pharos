## 灯塔

### 项目概述<br>

该应用为“灯塔导航”系统的接口服务，提供数据服务，该项目结构参考“考拉架构”，具备轻量化、简洁的领域设计。<br>

### 技术栈<br>
Java<br>
SpringBoot<br>
MyBatis-Plus<br>
Mysql<br>
Redis<br>
Maven<br>

### 项目结构<br>
#### pharos-app<br>
&nbsp;&nbsp;&nbsp;&nbsp;--业务组装层<br>
#### pharos-common<br>
&nbsp;&nbsp;&nbsp;&nbsp;--存放项目公用类、工具类、线程池配置类等<br>
#### pharos-domain<br>
&nbsp;&nbsp;&nbsp;&nbsp;--领域限定，包括领域用到的常量、枚举、DTO对象等<br>
&nbsp;&nbsp;&nbsp;&nbsp;--gateway interface<br>
#### pharos-infrasturcture<br>
&nbsp;&nbsp;&nbsp;&nbsp;--gateway实现<br>
&nbsp;&nbsp;&nbsp;&nbsp;--数据库表映射对象<br>
&nbsp;&nbsp;&nbsp;&nbsp;--定时任务<br>
&nbsp;&nbsp;&nbsp;&nbsp;--数据库Mapper
#### pharos-web<br>
&nbsp;&nbsp;&nbsp;&nbsp;--项目启动入口<br>
&nbsp;&nbsp;&nbsp;&nbsp;--配置文件<br>
&nbsp;&nbsp;&nbsp;&nbsp;--http入口

### 如何运行
#### 环境准备
在运行本项目之前，请确保您的开发环境中安装了以下软件：<br>
1. JDK [1.8]<br>
2. Maven <br>
3. Mysql [8.0]<br>
4. Redis<br>
#### 构建与运行
1. 克隆项目到本地
```java
git clone https://github.com/super-denny/pharos.git
```
2. 进入项目目录
```java
cd pharos
```
3. 使用Maven构建项目
```java
mvn clean install
```
4. 运行项目<br>
<b>先根据自己的环境修改配置文件中的数据库地址，邮箱信息等，然后创建pharos数据库，执行项目sql文件夹中的sql语句<br>
   </b></br>
如果是Spring Boot项目，您可以使用Maven插件来运行
```java
mvn spring-boot:run
```
如果您使用IDE（如IntelliJ IDEA或Eclipse），则可以直接在IDE中运行主类

####  开源协议

Pharos 遵循 [MIT 协议](https://github.com/super-denny/pharos/blob/master/LICENSE)。