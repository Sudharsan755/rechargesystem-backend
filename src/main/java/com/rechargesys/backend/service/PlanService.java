package com.rechargesys.backend.service;

import com.rechargesys.backend.entity.Plan;
import com.rechargesys.backend.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> getAllPlans(String operator) {
        if (operator != null && !operator.trim().isEmpty()) {
            return planRepository.findByOperatorIgnoreCase(operator.trim());
        }
        return planRepository.findAllByOrderByOperatorAscPriceAsc();
    }

    public Optional<Plan> getPlanById(String id) {
        return planRepository.findById(id);
    }

    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan updatePlan(String id, Plan updatedPlan) {
        return planRepository.findById(id).map(existing -> {
            if (updatedPlan.getOperator() != null) {
                existing.setOperator(updatedPlan.getOperator());
            }
            if (updatedPlan.getPrice() != null) {
                existing.setPrice(updatedPlan.getPrice());
            }
            if (updatedPlan.getData() != null) {
                existing.setData(updatedPlan.getData());
            }
            if (updatedPlan.getValidity() != null) {
                existing.setValidity(updatedPlan.getValidity());
            }
            return planRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + id));
    }

    public Plan deletePlan(String id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + id));
        planRepository.delete(plan);
        return plan;
    }
}

