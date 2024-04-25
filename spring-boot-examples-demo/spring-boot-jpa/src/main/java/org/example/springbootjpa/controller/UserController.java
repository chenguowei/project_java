package org.example.springbootjpa.controller;

import org.example.springbootjpa.dao.UserOrderDao;
import org.example.springbootjpa.entity.TUser;
import org.example.springbootjpa.repository.TUserRepository;
import org.example.springbootjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    TUserRepository tUserRepository;

    @Autowired
    UserService userService;

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

    @PostMapping("deleteUserSql")
    public String deleteUsersSql(@RequestParam(value = "age") String age) {
        System.out.println("deleteUsersSql: " + 10);
        int result = tUserRepository.deleteTUser(age);
        System.out.println("deleteUsersSql result: " + result);

        return "success " + result;
    }

    @PostMapping("/transaction")
    public String transaction(@RequestParam(value = "age")String age) {
        System.out.println("transaction: " + 10);
        try {
            userService.register(age);
        } catch (Exception e) {
           System.out.println("执行事务异常:" + e.getMessage());
        }
        return "success ";
    }

    // 联表查询
    @GetMapping("findViewInfo")
    public List<UserOrderDao> findViewInfo() {

        return tUserRepository.findViewInfo();
    }
}
