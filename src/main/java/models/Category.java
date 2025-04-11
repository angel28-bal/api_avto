package models;

import com.fasterxml.jackson.annotation.JsonProperty;

// Модель категории питомца
public class Category {
    // Уникальный идентификатор категории
    @JsonProperty
    private Long id;
    
    // Название категории
    @JsonProperty
    private String name;

    // Конструктор по умолчанию
    public Category() {}

    /**
     * Создает новую категорию с указанными параметрами.
     * 
     * @param id уникальный идентификатор категории
     * @param name название категории
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 