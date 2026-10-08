package com.rechargesys.backend.repository;

import com.rechargesys.backend.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, String> {
    List<Recharge> findByUserPhone(String userPhone);
    List<Recharge> findByOperatorIgnoreCase(String operator);
}

