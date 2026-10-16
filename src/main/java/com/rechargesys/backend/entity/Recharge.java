package com.rechargesys.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "recharges")
public class Recharge {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "operator", nullable = false)
    private String operator;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "\"paymentMethod\"", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "\"userPhone\"", nullable = false)
    private String userPhone;

    public Recharge() {
    }

    public Recharge(String id, String mobile, String operator, Integer amount,
                    String paymentMethod, String status, String date, String userPhone) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.mobile = mobile;
        this.operator = operator;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.date = date;
        this.userPhone = userPhone;
    }

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
        if (this.status == null || this.status.isBlank()) {
            this.status = "Success";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

