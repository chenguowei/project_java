package com.spring.cloud.product.facade;
import com.spring.cloud.common.pojo.UserInfo;
import com.spring.cloud.common.vo.ResultMessage;
public interface R4jFacade {
    public ResultMessage exp(String msg);

    public UserInfo getUser(Long id);

    public UserInfo getUser3(Long id);

    public ResultMessage exp();

    public UserInfo cacheUserInfo(Long id);

    public ResultMessage timeout();

    public UserInfo mixUserInfo(Long id);
}