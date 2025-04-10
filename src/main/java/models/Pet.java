package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private Category category;
    
    @JsonProperty
    private String name;
    
    @JsonProperty
    private List<String> photoUrls;
    
    @JsonProperty
    private List<Tag> tags;
    
    @JsonProperty
    private String status;

    // Конструктор по умолчанию
    public Pet() {}

    // Конструктор с параметрами
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