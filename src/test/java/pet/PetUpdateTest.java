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
import utils.ApiClient;
import utils.TestDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Pet Store API")
@Feature("Pet Management")
public class PetUpdateTest {
    
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
        testPet.setId(petId);
    }
    
    @Test
    @Description("Обновление имени и статуса существующего питомца")
    public void testUpdateExistingPet() {
        // Изменяем данные питомца
        String newName = "Updated " + testPet.getName();
        String newStatus = "sold";
        testPet.setName(newName);
        testPet.setStatus(newStatus);
        
        // Отправляем запрос на обновление
        Response updateResponse = ApiClient.put("/pet", testPet);
        
        // Проверяем результат
        assertEquals(200, updateResponse.getStatusCode(), "Неверный статус код при обновлении");
        
        Pet updatedPet = updateResponse.as(Pet.class);
        assertNotNull(updatedPet, "Обновленный питомец не должен быть null");
        assertEquals(newName, updatedPet.getName(), "Имя питомца не обновилось");
        assertEquals(newStatus, updatedPet.getStatus(), "Статус питомца не обновился");
    }
    
    @Test
    @Description("Попытка обновления несуществующего питомца")
    public void testUpdateNonExistingPet() {
        testPet.setId(999999999L);
        Response updateResponse = ApiClient.put("/pet", testPet);
        
        assertEquals(404, updateResponse.getStatusCode(), "Ожидается код 404 для несуществующего питомца");
    }
}