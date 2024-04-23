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
//        TUser tUser = new TUser();
//        tUser.setAge(age);
        int result = tUserRepository.deleteTUsersByAge(age);
        System.out.println("deleteTUsersByAge result: " + result);

        return "success " + result;
    }
}
