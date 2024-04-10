package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dao.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM t_order")
    List<Order> findAllOrders();
}
