package org.example.springbootmultidatasource.controller;

import org.example.springbootmultidatasource.dao.User;
import org.example.springbootmultidatasource.mapper.dev.DevUserMapper;
import org.example.springbootmultidatasource.mapper.local.LocalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public void setDevUserMapper(DevUserMapper devUserMapper) {
        this.devUserMapper = devUserMapper;
    }

    @Autowired
    public void setLocalUserMapper(LocalUserMapper localUserMapper) {
        this.localUserMapper = localUserMapper;
    }

    private DevUserMapper devUserMapper;

    private LocalUserMapper localUserMapper;

    @GetMapping("/getDevUsers")
    public List<User> getDevUsers() {
        return devUserMapper.findUsers();
    }

    @GetMapping("/getLocalUsers")
    public List<User> getLocalUsers() {
        return localUserMapper.findUsers();
    }
}
