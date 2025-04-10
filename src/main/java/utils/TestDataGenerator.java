package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import models.Category;
import models.Pet;
import models.Tag;

public class TestDataGenerator {
    
    public static Pet generateRandomPet() {
        String randomName = "Pet_" + UUID.randomUUID().toString().substring(0, 8);
        Pet pet = new Pet(randomName, "available");
        
        // Установка категории
        pet.setCategory(new Category(1L, "Dogs"));
        
        // Установка фото URL
        pet.setPhotoUrls(Collections.singletonList("http://example.com/pet.jpg"));
        
        // Установка тегов
        pet.setTags(Arrays.asList(
            new Tag(1L, "friendly"),
            new Tag(2L, "vaccinated")
        ));
        
        return pet;
    }
    
    public static Pet generatePetWithStatus(String status) {
        Pet pet = generateRandomPet();
        pet.setStatus(status);
        return pet;
    }
    
    public static Pet generatePetWithName(String name) {
        Pet pet = generateRandomPet();
        pet.setName(name);
        return pet;
    }
} 