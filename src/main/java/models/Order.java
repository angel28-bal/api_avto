package models;

import com.fasterxml.jackson.annotation.JsonProperty;

// Модель заказа в магазине питомцев
public class Order {
    // Уникальный идентификатор заказа
    @JsonProperty
    private Long id;
    
    // Идентификатор заказанного питомца
    @JsonProperty
    private Long petId;
    
    // Количество заказанных питомцев
    @JsonProperty
    private Integer quantity;
    
    // Дата доставки заказа
    @JsonProperty
    private String shipDate;
    
    // Статус заказа (placed, approved, delivered)
    @JsonProperty
    private String status;
    
    // Флаг завершенности заказа
    @JsonProperty
    private Boolean complete;

    // Конструктор по умолчанию
    public Order() {}

    /**
     * Создает новый заказ с указанными параметрами.
     * 
     * @param petId идентификатор заказываемого питомца
     * @param quantity количество питомцев
     * @param status статус заказа
     */
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