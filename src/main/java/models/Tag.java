package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private String name;

    // Конструктор по умолчанию
    public Tag() {}

    // Конструктор с параметрами
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