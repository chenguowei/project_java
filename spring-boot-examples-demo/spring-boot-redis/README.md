# Getting Started

### 引入依赖

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
```



### 配置redis

```yaml
spring:
  application:
    name: spring-boot-redis
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      password: ningzaichun
      database: 0
      lettuce:
        pool:
          enabled: true
          max-idle: 16
          max-active: 32
          min-idle: 8
```



### 注册redisTemplate

```java
package org.example.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        StringRedisSerializer serializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer        );
        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }
}
```



### 编写工具类

```java
package org.example.springbootredis.redisutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
}
```



### 编写 Controller测试

```java
package org.example.springbootredis.controller;

import org.example.springbootredis.redisutil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/setKey")
    public void setKey(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        System.out.println("setKey key: " + key + ", value: " + value);
        redisUtil.set(key, value);
    }

    @PostMapping("/getKey")
    public String getKey(@RequestParam(value = "key") String key) {
        System.out.println("getKey key: " + key);
        Object value = redisUtil.get(key);

        return (String) value;
    }

    @PostMapping("/delKey")
    public void delKey(@RequestParam(value = "key") String key) {
        System.out.println("delKey key: " + key);
        redisUtil.del(key);
    }
}
```



### Github 

完整的代码：https://github.com/chenguowei/project_java/blob/main/spring-boot-examples-demo/spring-boot-redis/README.md
