package com.rechargesys.backend.controller;

import com.rechargesys.backend.entity.Recharge;
import com.rechargesys.backend.service.RechargeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
@CrossOrigin(origins = "*")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // GET /api/recharges or /api/recharges?userPhone=... or /api/recharges?operator=...
    @GetMapping
    public ResponseEntity<List<Recharge>> getAllRecharges(
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) String operator) {
        return ResponseEntity.ok(rechargeService.getAllRecharges(userPhone, operator));
    }

    // GET /api/recharges/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Recharge> getRechargeById(@PathVariable String id) {
        return rechargeService.getRechargeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/recharges
    @PostMapping
    public ResponseEntity<Recharge> createRecharge(@RequestBody Recharge recharge) {
        Recharge created = rechargeService.createRecharge(recharge);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/recharges/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Recharge> updateRechargeWithPathVariable(
            @PathVariable String id,
            @RequestBody Recharge recharge) {
        return ResponseEntity.ok(rechargeService.updateRecharge(id, recharge));
    }

    // PUT /api/recharges (with id in request body)
    @PutMapping
    public ResponseEntity<?> updateRechargeWithBodyId(@RequestBody Recharge recharge) {
        if (recharge.getId() == null || recharge.getId().isBlank()) {
            return ResponseEntity.badRequest().body("Recharge id is required in request body");
        }
        return ResponseEntity.ok(rechargeService.updateRecharge(recharge.getId(), recharge));
    }

    // DELETE /api/recharges/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Recharge> deleteRechargeWithPathVariable(@PathVariable String id) {
        return ResponseEntity.ok(rechargeService.deleteRecharge(id));
    }

    // DELETE /api/recharges?id=...
    @DeleteMapping
    public ResponseEntity<?> deleteRechargeWithQueryParam(@RequestParam(required = false) String id) {
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().body("Query parameter 'id' is required");
        }
        return ResponseEntity.ok(rechargeService.deleteRecharge(id));
    }
}

