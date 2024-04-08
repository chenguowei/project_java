package org.example.controller;

import org.example.dao.Order;
import org.example.dao.User;
import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

//    @Autowired
//    public void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    private UserMapper userMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    private OrderMapper orderMapper;

//    @GetMapping("/getusers")
//    public List<User> getUsers() {
//
//        return this.userMapper.selectUserList();
//    }

    @GetMapping("/getorders")
    public List<Order> getOrders() {
        return this.orderMapper.findAllOrders();
    }
}
