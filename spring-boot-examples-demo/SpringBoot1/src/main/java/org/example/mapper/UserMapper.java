package org.example.mapper;

import org.example.dao.User;

import java.util.List;

public interface UserMapper {

    public List<User> selectUserList();
}
