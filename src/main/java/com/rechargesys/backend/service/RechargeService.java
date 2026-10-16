package com.rechargesys.backend.service;

import com.rechargesys.backend.entity.Recharge;
import com.rechargesys.backend.repository.RechargeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RechargeService {

    private final RechargeRepository rechargeRepository;

    public RechargeService(RechargeRepository rechargeRepository) {
        this.rechargeRepository = rechargeRepository;
    }

    public List<Recharge> getAllRecharges(String userPhone, String operator) {
        if (userPhone != null && !userPhone.trim().isEmpty()) {
            return rechargeRepository.findByUserPhone(userPhone.trim());
        }
        if (operator != null && !operator.trim().isEmpty() && !operator.equalsIgnoreCase("All")) {
            return rechargeRepository.findByOperatorIgnoreCase(operator.trim());
        }
        return rechargeRepository.findAll();
    }

    public Optional<Recharge> getRechargeById(String id) {
        return rechargeRepository.findById(id);
    }

    public Recharge createRecharge(Recharge recharge) {
        return rechargeRepository.save(recharge);
    }

    public Recharge updateRecharge(String id, Recharge updated) {
        return rechargeRepository.findById(id).map(existing -> {
            if (updated.getMobile() != null) {
                existing.setMobile(updated.getMobile());
            }
            if (updated.getOperator() != null) {
                existing.setOperator(updated.getOperator());
            }
            if (updated.getAmount() != null) {
                existing.setAmount(updated.getAmount());
            }
            if (updated.getPaymentMethod() != null) {
                existing.setPaymentMethod(updated.getPaymentMethod());
            }
            if (updated.getStatus() != null) {
                existing.setStatus(updated.getStatus());
            }
            if (updated.getDate() != null) {
                existing.setDate(updated.getDate());
            }
            if (updated.getUserPhone() != null) {
                existing.setUserPhone(updated.getUserPhone());
            }
            return rechargeRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Recharge record not found with id: " + id));
    }

    public Recharge deleteRecharge(String id) {
        Recharge recharge = rechargeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recharge record not found with id: " + id));
        rechargeRepository.delete(recharge);
        return recharge;
    }
}

