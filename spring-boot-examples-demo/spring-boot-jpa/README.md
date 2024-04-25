# Getting Started

## 简介

[Spring Data JPA](https://springdoc.cn/spring-data-jpa/) 是 Spring 框架提供的一个模块，用于简化与关系型数据库的交互和数据访问。它基于JPA（Java Persistence API）标准，并提供了一组易于使用的API和工具，帮助开发人员更轻松地进行数据库操作。通过Spring Data JPA，开发人员可以通过编写简洁的代码来执行常见的 CRUD 操作，同时还支持高级查询、分页、事务管理等功能。它的目标是提供一种更简单、更高效的方式来处理数据库操作，减少开发人员的工作量，并提高应用程序的可维护性和可扩展性。



## 创建工程

使用 idea 快速创建 Spring Boot 整合 Spring Boot JPA实例。我们选择了 `spring-boot-starter-web`、`spring-boot-starter-data-jpa`、`mysql-connector-j` 和 `spring-boot-starter-test` 依赖。

对应的pom.xml

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
```



### 创建实体 Entity

```java
@Entity
@Table(name = "t_user")
public class TUser {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
    private long id;

    @Column
    private String username;

    @Column
    private String age;
}
```

`@Entity` 表示这个类是一个需要受 `EntityManager` 管理的实体类。`@Table(name = "sys_user")` 注解指定了实体在数据库中所对应的表名称。`@Id` 用于标识ID字段，`@GeneratedValue(strategy = GenerationType.IDENTITY)` 注解指定了 ID 值的生成方式，其中 `GenerationType.IDENTITY` 表示主键由数据库自动生成（自增）。`@Column` 注解表示对象字段和数据表列的映射关系。



### TUser对应的SQL

```sql
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8
```



### 创建 Repository 接口

```java
package org.example.springbootjpa.repository;

import org.example.springbootjpa.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TUserRepository extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

    List<TUser> findTUsersByAge(String age);

    @Transactional
    int deleteTUsersByAge(String age);
}

```

通过继承 `JpaRepository`、`JpaSpecificationExecutor` 就可以获得已经预定义的各种 CRUD 方法。其中 `JpaRepository` 的泛型对象是实体类型和 ID 类型，`JpaSpecificationExecutor` 的泛型对象只有实体类型。

使用 `@Repository` 注解表示这是一个 `Repository` 接口。

@Transactional 代表启用事务。因为By删除操作，是先执行 select by id, 然后通过 id 逐条删除记录的，所以需要事务。



### 配置 application.yml

```
spring:
  application:
    name: spring-boot-jpa

  # 数据源配置
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/kgc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8&allowMultiQueries=true
    username: root
    password: root

  jpa:
    database-platform: org.hibernate.dialect.MySQLDialect
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        hbm2ddl:
          auto: none
logging:
  level:
    org:
      hibernate:
        orm:
          jdbc:
            bind: "TRACE"
```



上述常用配置中，数据源的配置是必须的。其他配置项都有默认值。

其中 `spring.jpa.hibernate.ddl-auto` 比较重要，表示建表的策略，可选的枚举值如下：

- `create`：不管表是否存在，每次启动都会重新建表（会导致数据丢失）。
- `create-drop`：启动的时候创建表，程序退出（`SessionFactory` 关闭）的时候删除表。
- `none`：不进行任何操作。
- `update`：如果数据表不存在则创建，在实体对象被修改后，下次启动重新修改表结构（不会删除已经存在的数据）。
- `validate`：启动的时候验证数据表的结构。

建议使用 `none`，手动维护数据表结构，以避免不小心修改了实体对象后导致表结构被修改，甚至是数据丢失。



### 编写Controller

```java
package org.example.springbootjpa.controller;

import org.example.springbootjpa.entity.TUser;
import org.example.springbootjpa.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    TUserRepository tUserRepository;

    @PostMapping("/addUser")
    public String addUser() {
        Random rand = new Random();
        TUser tUser = new TUser();
        tUser.setAge("10");
        tUser.setUsername("chenguowei"+ rand.nextInt(100));
        tUserRepository.save(tUser);

        return "success";
    }

    @GetMapping("/getUsersByAge")
    public List<TUser> getUsesrByAge(@RequestParam(value = "age", required = true) String age) {
        System.out.println("getUserByAge: " + age);
        return  tUserRepository.findTUsersByAge(age);
    }

    @PostMapping("deleteUsersByAge")
    public String deleteUsersByAge(@RequestParam(value = "age", required = true) String age) {
        System.out.println("deleteUsersByAge: " + 10);
        int result = tUserRepository.deleteTUsersByAge(age);
        System.out.println("deleteTUsersByAge result: " + result);

        return "success " + result;
    }
}
```

这里使用字段注入的方式 @Autowired, 实际中最好使用 setXXX()注入方式。



### 编写SQL

```java
 @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from t_user where age = ?1")
    int deleteTUser(String age);
```



### 事务管理

```java
 @Transactional(rollbackFor = Exception.class)
    public void register(String age) throws Exception {
        TUser user = new TUser();
        user.setAge(age);
        user.setUsername("chenguowei");

        tUserRepository.save(user);
        if (Objects.equals(age, "10")) {
            throw new Exception("测试事务");
        }

        TOrder tOrder = new TOrder();
        tOrder.setName("chenguowei");
        tOrder.setNumber(age);

        tOrderRepository.save(tOrder);
    }
```

- ​	多个方法嵌套调用，如果都有 @Transactional 注解，则产生事务传递，默认 Propagation.REQUIRED。
- ​	事务默认只对 RutimeException 回滚，而非 Exception回滚。
- ​	如果要对 checked Exceptions 进行回滚，则需要 @Transactional(rollbackFor = Exception.class)



### 联表查询

新建一个联表的结构体

```java
package org.example.springbootjpa.dao;

public interface UserOrderDao {
    String getName();
    String getNumber();
    String getUsername();
    String getAge();
}
```

自定义联表查询语句

```java
@Query(nativeQuery = true, value = "select username, age, number, name " +
            "from t_user left join t_order ON t_user.age = t_order.number")
    public List<UserOrderDao> findViewInfo();
```



### 测试
curl -XGET 'http://localhost:8080/user/getUsersByAge?age=10'

curl -XPOST 'http://localhost:8080/user/addUser'

curl -XPOST 'http://localhost:8080/user/deleteUserSql?age=10'

curl -XPOST 'http://localhost:8080/user/transaction?age=10'

curl -XGET 'http://localhost:8080/user/findViewInfo'



### Github 

完整的代码：https://github.com/chenguowei/project_java/blob/main/spring-boot-examples-demo/spring-boot-jpa



