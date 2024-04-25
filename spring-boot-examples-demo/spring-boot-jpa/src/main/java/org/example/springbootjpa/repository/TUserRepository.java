package org.example.springbootjpa.repository;

import org.example.springbootjpa.dao.UserOrderDao;
import org.example.springbootjpa.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TUserRepository extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

    List<TUser> findTUsersByAge(String age);

    @Transactional
    int deleteTUsersByAge(String age);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from t_user where age = ?1")
    int deleteTUser(String age);

    @Query(nativeQuery = true, value = "select username, age, number, name " +
            "from t_user left join t_order ON t_user.age = t_order.number")
    public List<UserOrderDao> findViewInfo();




}
