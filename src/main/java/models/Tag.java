package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель тега для питомца.
 * Используется для добавления дополнительных характеристик питомцу.
 */
public class Tag {
    // Уникальный идентификатор тега
    @JsonProperty
    private Long id;
    
    // Название тега
    @JsonProperty
    private String name;

    // Конструктор по умолчанию
    public Tag() {}

    /**
     * Создает новый тег с указанными параметрами.
     * 
     * @param id уникальный идентификатор тега
     * @param name название тега
     */
    public Tag(Long id, String name) {
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