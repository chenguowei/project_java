package org.example.springbootjpa.service;

import org.example.springbootjpa.entity.TOrder;
import org.example.springbootjpa.entity.TUser;
import org.example.springbootjpa.repository.TOrderRepository;
import org.example.springbootjpa.repository.TUserRepository;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private TUserRepository tUserRepository;
    @Autowired
    private TOrderRepository tOrderRepository;


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

}
