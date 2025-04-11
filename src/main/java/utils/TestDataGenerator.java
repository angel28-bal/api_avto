package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import models.Category;
import models.Pet;
import models.Tag;

/**
 * Класс для генерации тестовых данных.
 * Содержит методы для создания объектов с различными наборами полей.
 * Используется в тестах для создания тестовых объектов.
 */
public class TestDataGenerator {
    
    /**
     * Генерирует случайного питомца со всеми заполненными полями.
     * Создает уникальное имя питомца с помощью UUID.
     * Устанавливает стандартные значения для всех полей:
     * - Категория: Dogs (id: 1)
     * - Фото: одно стандартное фото
     * - Теги: friendly и vaccinated
     * - Статус: available
     * 
     * @return Pet объект со случайным именем и заполненными полями
     */
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
    
    /**
     * Генерирует питомца с указанным статусом.
     * Создает питомца через generateRandomPet() и меняет его статус.
     * 
     * @param status новый статус питомца
     * @return Pet объект с указанным статусом
     */
    public static Pet generatePetWithStatus(String status) {
        Pet pet = generateRandomPet();
        pet.setStatus(status);
        return pet;
    }
    
    /**
     * Генерирует питомца с указанным именем.
     * Создает питомца через generateRandomPet() и меняет его имя.
     * 
     * @param name новое имя питомца
     * @return Pet объект с указанным именем
     */
    public static Pet generatePetWithName(String name) {
        Pet pet = generateRandomPet();
        pet.setName(name);
        return pet;
    }
} 