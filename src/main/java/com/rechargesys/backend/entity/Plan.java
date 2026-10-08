package com.rechargesys.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "operator", nullable = false)
    private String operator;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "validity", nullable = false)
    private String validity;

    public Plan() {
    }

    public Plan(String id, String operator, Integer price, String data, String validity) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.operator = operator;
        this.price = price;
        this.data = data;
        this.validity = validity;
    }

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}

