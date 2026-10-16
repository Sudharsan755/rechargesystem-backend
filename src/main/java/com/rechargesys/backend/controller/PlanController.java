package com.rechargesys.backend.controller;

import com.rechargesys.backend.entity.Plan;
import com.rechargesys.backend.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin(origins = "*")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // GET /api/plans or /api/plans?operator=Airtel
    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans(@RequestParam(required = false) String operator) {
        return ResponseEntity.ok(planService.getAllPlans(operator));
    }

    // GET /api/plans/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable String id) {
        return planService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/plans
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan created = planService.createPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/plans/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlanWithPathVariable(
            @PathVariable String id,
            @RequestBody Plan plan) {
        return ResponseEntity.ok(planService.updatePlan(id, plan));
    }

    // PUT /api/plans (with id in request body)
    @PutMapping
    public ResponseEntity<?> updatePlanWithBodyId(@RequestBody Plan plan) {
        if (plan.getId() == null || plan.getId().isBlank()) {
            return ResponseEntity.badRequest().body("Plan id is required in request body");
        }
        return ResponseEntity.ok(planService.updatePlan(plan.getId(), plan));
    }

    // DELETE /api/plans/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> deletePlanWithPathVariable(@PathVariable String id) {
        return ResponseEntity.ok(planService.deletePlan(id));
    }

    // DELETE /api/plans?id=...
    @DeleteMapping
    public ResponseEntity<?> deletePlanWithQueryParam(@RequestParam(required = false) String id) {
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().body("Query parameter 'id' is required");
        }
        return ResponseEntity.ok(planService.deletePlan(id));
    }
}

