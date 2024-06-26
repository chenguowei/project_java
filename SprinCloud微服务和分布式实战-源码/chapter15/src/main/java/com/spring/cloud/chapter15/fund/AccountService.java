package com.spring.cloud.chapter15.fund;

import com.spring.cloud.chapter15.params.FundParams;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**** imports ****/
@Component // ①
public class AccountService {

    // 消息监听，取YAML文件配置的队列名
    @RabbitListener(queues = "${rabbitmq.queue.fund}") // ②
    public void deelAccount(FundParams params) {
        System.out.println("扣减账户金额逻辑......");
    }
}