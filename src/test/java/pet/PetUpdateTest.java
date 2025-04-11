package pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.Pet;
import utils.ApiClient;
import utils.TestDataGenerator;

// Тесты для обновления информации о питомцах
@Epic("Pet Store API")
@Feature("Pet Management")
public class PetUpdateTest {
    
    private Pet testPet;
    private Long petId;
    
    // Инициализация тестового окружения
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    // Подготовка тестовых данных перед каждым тестом
    @BeforeEach
    public void setUp() {
        // Создаем тестового питомца
        testPet = TestDataGenerator.generateRandomPet();
        Response createResponse = ApiClient.post("/pet", testPet);
        assertEquals(200, createResponse.getStatusCode(), "Не удалось создать тестового питомца");
        
        Pet createdPet = createResponse.as(Pet.class);
        petId = createdPet.getId();
    }
    
    // Тест обновления существующего питомца
    @Test
    @Description("Обновление информации о существующем питомце")
    public void testUpdateExistingPet() {
        // Создаем обновленные данные для питомца
        Pet updatedPet = TestDataGenerator.generateRandomPet();
        updatedPet.setId(petId);
        
        Response response = ApiClient.put("/pet", updatedPet);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        Pet retrievedPet = response.as(Pet.class);
        assertNotNull(retrievedPet, "Обновленный питомец не должен быть null");
        assertEquals(petId, retrievedPet.getId(), "ID питомца не совпадает");
        assertEquals(updatedPet.getName(), retrievedPet.getName(), "Имя питомца не обновилось");
    }
    
    // Тест попытки обновления несуществующего питомца
    @Test
    @Description("Попытка обновления несуществующего питомца")
    public void testUpdateNonExistingPet() {
        Pet nonExistingPet = TestDataGenerator.generateRandomPet();
        nonExistingPet.setId(999999999L);
        
        Response response = ApiClient.put("/pet", nonExistingPet);
        
        assertEquals(404, response.getStatusCode(), "Ожидается код 404 для несуществующего питомца");
    }
}