package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

// Модель питомца в магазине
public class Pet {
    // Уникальный идентификатор питомца
    @JsonProperty
    private Long id;
    
    // Категория питомца
    @JsonProperty
    private Category category;
    
    // Имя питомца
    @JsonProperty
    private String name;
    
    // Список URL фотографий питомца
    @JsonProperty
    private List<String> photoUrls;
    
    // Список тегов питомца
    @JsonProperty
    private List<Tag> tags;
    
    // Статус питомца (available, pending, sold)
    @JsonProperty
    private String status;

    // Конструктор по умолчанию
    public Pet() {}

    /**
     * Создает нового питомца с указанными параметрами.
     * 
     * @param name имя питомца
     * @param status статус питомца
     */
    public Pet(String name, String status) {
        this.name = name;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}