package org.example.springbootjpa.repository;

import org.example.springbootjpa.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TUserRepository extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

    List<TUser> findTUsersByAge(String age);

    @Transactional
    int deleteTUsersByAge(String age);
}
