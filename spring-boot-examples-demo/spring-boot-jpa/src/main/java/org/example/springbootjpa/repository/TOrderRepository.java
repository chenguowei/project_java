package org.example.springbootjpa.repository;

import org.example.springbootjpa.entity.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TOrderRepository extends JpaRepository<TOrder, Long>, JpaSpecificationExecutor<TOrder> {

}
