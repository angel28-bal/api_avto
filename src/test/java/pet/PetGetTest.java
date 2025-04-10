package pet;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ApiClient;
import utils.TestDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Pet Store API")
@Feature("Pet Management")
public class PetGetTest {
    
    private Pet testPet;
    private Long petId;
    
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    @BeforeEach
    public void setUp() {
        // Создаем тестового питомца
        testPet = TestDataGenerator.generateRandomPet();
        Response createResponse = ApiClient.post("/pet", testPet);
        assertEquals(200, createResponse.getStatusCode(), "Не удалось создать тестового питомца");
        
        Pet createdPet = createResponse.as(Pet.class);
        petId = createdPet.getId();
    }
    
    @Test
    @Description("Получение питомца по существующему ID")
    public void testGetExistingPet() {
        Response response = ApiClient.get("/pet/" + petId);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        Pet retrievedPet = response.as(Pet.class);
        assertNotNull(retrievedPet, "Полученный питомец не должен быть null");
        assertEquals(petId, retrievedPet.getId(), "ID питомца не совпадает");
        assertEquals(testPet.getName(), retrievedPet.getName(), "Имя питомца не совпадает");
    }
    
    @Test
    @Description("Попытка получения питомца по несуществующему ID")
    public void testGetNonExistingPet() {
        Response response = ApiClient.get("/pet/999999999");
        
        assertEquals(404, response.getStatusCode(), "Ожидается код 404 для несуществующего питомца");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    @Description("Поиск питомцев по статусу")
    public void testFindPetsByStatus(String status) {
        // Создаем питомца с нужным статусом
        Pet pet = TestDataGenerator.generatePetWithStatus(status);
        ApiClient.post("/pet", pet);
        
        // Ищем питомцев по статусу
        Response response = ApiClient.get("/pet/findByStatus?status=" + status);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        Pet[] pets = response.as(Pet[].class);
        assertNotNull(pets, "Список питомцев не должен быть null");
        
        // Проверяем, что все найденные питомцы имеют правильный статус
        for (Pet foundPet : pets) {
            assertEquals(status, foundPet.getStatus(), 
                "Найден питомец с неправильным статусом");
        }
    }
}