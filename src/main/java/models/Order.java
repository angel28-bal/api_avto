package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private Long petId;
    
    @JsonProperty
    private Integer quantity;
    
    @JsonProperty
    private String shipDate;
    
    @JsonProperty
    private String status; // placed, approved, delivered
    
    @JsonProperty
    private Boolean complete;

    // Конструктор по умолчанию
    public Order() {}

    // Конструктор с параметрами
    public Order(Long petId, Integer quantity, String status) {
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = false;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}