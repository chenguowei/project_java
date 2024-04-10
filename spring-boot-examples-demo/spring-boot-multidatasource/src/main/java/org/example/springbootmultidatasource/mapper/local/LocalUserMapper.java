package org.example.springbootmultidatasource.mapper.local;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.springbootmultidatasource.dao.User;

import java.util.List;

@Mapper
public interface LocalUserMapper {

    @Select("SELECT * FROM t_user")
    List<User> findUsers();
}
