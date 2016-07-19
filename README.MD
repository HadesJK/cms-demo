# SpringMVC Demo

[TOC]

**学习java web开发也有2年时间有余了，趁上班之前赶紧总结了一些经验教训。**

## 开发框架
主要涉及到以下开源框架

1. spring

2. springMVC

3. velocity

4. mybatis（数据库ORM）

5. jetty（web 容器）

------

## web基础

### 目录结构

web的目录结构一般如下

```
webapp
   WEB-INF
      web.xml
```

web.xml我一般以servlet的知识来理解

### 几个标签的解释

- web-app
这个是根元素，至于怎么写，同学，看你用servlet版本了。
我用servlet3.1， 那么是这样子的：
```
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
		 http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
</web-app>
```
至于你用servlet2.5或者servlet3.0 那么自己去网上搜吧，我想很少有人能背出这行代码来的。

- welcome-file-list和welcome-file
welcome-file-list 下可能有好几个 welcome-file。
显示时按顺序从第一个找起，如果第一个存在，就显示第一个，后面的不起作用。
如果第一个不存在，就找第二个，以此类推。
关于欢迎页面：访问一个网站时，默认看到的第一个页面就叫欢迎页，一般情况下是由首页来充当欢迎页的。
一般情况下，我们会在web.xml中指定欢迎页。
但web.xml并不是一个Web的必要文件，没有web.xml，网站仍然是可以正常工作的。（具体怎么工作的，我也不晓得。。。）
对于tomcat来说，当你只指定一个web的根名，没有指定具体页面，
去访问时一个web时，如果web.xml文件中配置了欢迎页，那么就返回指定的那个页面作为欢迎页，
如果没有web.xml文件，或虽然有web.xml，但web.xml也没指定欢迎页的情况下，
**它默认先查找index.html文件**，如果找到了，就把index.html作为欢迎页还回给浏览器。
**如果没找到index.html，tomcat就去找index.jsp**。找到index.jsp就把它作为欢迎页面返回。
如果index.html和index.jsp都没找到，又没有用web.xml文件指定欢迎页面，那此时tomcat就不知道该返回哪个文件了，
它就显示The requested resource (/XXX) is not available(我就出现过这个问题)的页面。
其中XXX表示web的根名。但如果你指定了具体页面，是可以正常访问的。
**事实上，在我的工程里，jetty也是一样的**
这两位哥们写的很好
http://blog.csdn.net/shanliangliuxing/article/details/7458492
http://www.cnblogs.com/hxsyl/p/3435412.html

- display-name
这个名字实际上对于项目的运行完全没有必要。
但是最好配上去，为毛呢？还需要维护这个工程的话，那么这个友好的名字很重要。
例如，tomcat 管理页面中可以显示当前的web应用。

- 其它的元素
之后有时间再补上吧。。。

------

## SpringMVC

之前使用了很长时间的struts2，偶然的机会用到了springMVC，之后决定再也不用struts2了。
springMVC既可以开发web，也能开发webService。

**SpringMVC的学习，我觉得最重要的是学习几个注解**

### RequestMapping
在web和web service开发中，这个注解都听重要的。 首先看一下它的源码：

```java
public @interface RequestMapping {
    String name() default "";

    String[] value() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};
}
```
 它有6个字段。

1. value
指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；

2. method
指定请求的method类型， GET、POST、PUT、DELETE等；

3. params
指定request中必须包含某些参数值是，才让该方法处理。

4. headers
指定request中必须包含某些指定的header值，才能让该方法处理请求。

5. consumes
指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;

6. produces
指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

不一一详解了，随便举些案例，以controller中的StudentsController.java为例，代码如下：

```java
package com.yifan.cms.demo.controller;

import com.yifan.cms.demo.entity.Student;
import com.yifan.cms.demo.service.JsonService;
import com.yifan.cms.demo.service.StudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 21:59
 */
@Controller
@RequestMapping("/students")
public class StudentsController {
    @Resource
    private StudentsService studentsService;

    @Resource
    private JsonService jsonService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentsService.getAllMembers());
        return "students/list";
    }

    @RequestMapping("/userAdd")
    public String userAddIndex() {
        return "students/userAdd";
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(String name, String password, String number, String clazz, Integer age, String sex, String phone) {
        studentsService.addUser(name, password, number, clazz, age, sex, phone);
        return new ModelAndView("redirect:/students/list");
    }

    //返回json
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Student showUser(@PathVariable String username) {
        System.out.println("query username is " + username);
        return jsonService.queryStu();
    }

}

```

### ResponseBody

SpringMVC对json的支持，用到一个注解 **@ResponseBody**。

需要注意的是，返回json的情况下，需要在pom.xml中加入jackson的包

```java
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>
```

以下是我在浏览器输入**http://localhost:8090/students/jql**

返回结果 **{"id":null,"name":"Hades","password":"123456","number":"07110113110","clazz":"IEEE","age":18,"sex":"male","phone":"1595800"}**




------

## velocity

Velocity是一个基于Java的模板引擎（template engine），语法简单。

用过struts2的同学就知道了，语法太复杂了，完全搞不懂啊。

velocity 对变量的取值用到 **${varName}** 这样子的形式（是不是和spring的配置文件取变量的形式一样？）
也可以使用$varName的形式。
但是使用期间，要确保velocity变量和普通的字符串没有歧义。

- **"#"用来标识Velocity的脚本语句，包括#set、#if 、#else、#end、#foreach、#end、#iinclude、#parse、#macro等**
- **"$"用来标识一个对象(或理解为变量)**
- **"{}"用来明确标识Velocity变量(这样子可以防止歧义)**
- **"!"用来强制把不存在的变量显示为空白，没有使用"!"，那么当一个变量stu不存在时，前端会显示 "$stu" 这样子的形式**


常用到以下一些语法。

### 遍历List容器

```
#foreach ($element in $list)
    $element
#end
```
### 遍历Map

```
#foreach($data in $mapData.entrySet())
       <p>KEY: ${data.key}</p>
       <p>VALUE: ${data.value}</p>
#end
```

或者

```
#foreach ( $key in $mapData.keySet())
    <p>Key: $key</p>
    <p>Value: $list.get($key)</p>
#end
```

### 条件语句

```
#if($!stu.status ==  0)
    <p>审核成功</p>
#elseif($!stu.status == 1)
    <p>审核失败</p>
#elseif($!stu.status == 2)
    <p>等待审核</p>
#else
    <p>$!stu.status
#end
```

### 一些不常用的

**#set**

```
#set ($i=0)
#foreach($info in $list)
序号:$i --> $!info
#set($i=$i+1)
#end
```

## springMVC整合velocity

### 目录结构

我是用maven管理项目的，在resources文件夹下新建了一个springmvc文件夹，然后在该文件夹下添加了springMVC-servlet.xml文件
该文件的中添加velocity相关的内容如下：

```
 <!--velocity 配置-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver">
        <property name="prefix" value="pages/"/>
        <property name="suffix" value=".vm"/>
        <property name="cache" value="true"/>
        <property name="contentType" value="text/html;charset=UTF-8"/>
        <property name="dateToolAttribute" value="dateTool"/>
        <!--日期函数名称-->
        <property name="numberToolAttribute" value="numberTool"/>
        <!--数字函数名称-->
        <property name="exposeSpringMacroHelpers" value="true"/>
        <!--是否使用spring对宏定义的支持-->
        <property name="exposeRequestAttributes" value="true"/>
        <!--是否开放request属性-->
        <property name="requestContextAttribute" value="rc"/>
        <!--request属性引用名称-->
        <!--<property name="exposeSessionAttributes" value="true" />-->
        <!--指定layout文件-->
        <property name="layoutUrl" value="velocity/layout/blank.vm"/>
    </bean>
    <!-- 配置velocity引擎 -->
    <bean id="velocityConfigurer" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
        <property name="resourceLoaderPath" value="/WEB-INF/"/>
        <property name="velocityProperties">
            <props>
                <prop key="input.encoding">UTF-8</prop>
                <prop key="output.encoding">UTF-8</prop>
            </props>
        </property>
    </bean>
```

在WEB-INF文件夹下新建pages和velocity目录。

velocity目录下新建layout/blank.vm文件，内容如下：

```
$screen_content
```

根据文件名可知，这个文件什么内容都没有，关于velocity之后细讲。

### controller

controller 使用java编写，写一个简单的代码，直接返回页面。

```java
@Controller
public class springMVC {

    @RequestMapping("/springMVCVelocity")
    public String response() {
        return "spring/springMVC";
    }
}
```

根据之前的springMVC-servlet.xml配置文件的前缀"pages/"，后缀".vm"，
然后拼接这个返回字符串，就是： "pages/spring/springMVC.vm",
就去找这个文件呗。。。这个文件就是现实的页面，而使用空白的blank.vm装饰。
访问的url： localhost:21322/springMVCVelocity

------

## 使用开源前端模板

前端我太弱了，一般都是去网上找找开源的前端模板，这个demo中，我用到了charisma
可以从这里进行下载URL： https://github.com/usmanhalalit/charisma
然后在web的根目录下放置文件夹charisma，我喜欢用webapp作为根目录文件名，
网上也看到过webroot之类的。

**这里我觉得作者给大家挖了一个坑，特别是对我这些前段渣渣们来说！**
**link 标签里面有一个id字段，整合到工程里面千万千万要删掉！否则各种奇怪的错误！**
**浪费了哥哥一个下午的时间，哼。**

springMVC对静态资源有点小小的要求，就是不拦截静态资源，因此在springMVC-servlet.xml中对这些文件需要添加一些内容:

```
<mvc:resources location="/charisma/" mapping="/charisma/**"/>
```

------

## 补充说明

因为使用maven管理项目，遵循约定优于配置的方式，maven web 工程的目录结构一般如下

| 目录                  | 功能              |
| -------------         |:-------------:    |
| src/main/java         |项目的java代码
| src/main/resources    |项目的资源文件     |
| src/main/webapp       |web的根目录        |
| src/test/java         |测试的java代码     |
| src/test/resources    |测试的资源文件     |

------

## 整合mybatis

不知道现在还用Hibernate的还有多少人，从ibatis到mybatis，感觉这个ORM框架蛮好用的。
在resources文件夹下新建一个mysql文件夹，然后加入配置文件 mysql.properites

```
##JDBC Global Setting
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://10.13.81.185:3306/ct-lung?useUnicode=true&characterEncoding=utf-8
jdbc.username=root
jdbc.password=147lab122
```

并在springMVC-servlet.xml中添加以下内容

```
<!-- 引入jdbc配置文件 -->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations">
        <list>
            <value>classpath*:/mysql/mysql.properties</value>
        </list>
    </property>
    </bean>
    <!-- dataSource 配置 -->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>
    <!-- mybatis文件配置，扫描所有mapper文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="mapperLocations" value="classpath*:/mapper"/>
    </bean>
    <!-- spring与mybatis整合配置，扫描所有dao -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" >
        <property name="basePackage" value="com.yifan.cms.demo.mapper" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
    </bean>
```




