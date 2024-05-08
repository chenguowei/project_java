package org.example.serviceconfig.controller;

import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.example.serviceconfig.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class TestController {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserConfig userConfig;

    @GetMapping("getSource")
    public String dataSource() throws SQLException {
        String datasourceClass = dataSource.getClass().toString();
        boolean connectionFlag = dataSource.getConnection() != null;
        return "数据源类型：" + dataSource + ", 是否连接成功：" + connectionFlag + "";
    }

    @GetMapping("getUser")
    public String getUser() {
        return userConfig.getAge() + userConfig.getName();
    }
}
