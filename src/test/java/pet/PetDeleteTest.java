package pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@Epic("Pet Store API")
@Feature("Pet Management")
public class PetDeleteTest {
    
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
        
        // Проверяем, что питомец действительно создан
        Response getResponse = ApiClient.get("/pet/" + petId);
        assertEquals(200, getResponse.getStatusCode(), "Созданный питомец не найден");
    }
    
    @Test
    @Description("Удаление существующего питомца")
    public void testDeleteExistingPet() {
        // Проверяем, что питомец существует перед удалением
        Response getResponse = ApiClient.get("/pet/" + petId);
        assertEquals(200, getResponse.getStatusCode(), "Питомец не найден перед удалением");
        
        Response response = ApiClient.delete("/pet/" + petId);
        assertEquals(200, response.getStatusCode(), "Неверный статус код при удалении");
        
        // Проверяем, что питомец действительно удален
        Response getResponseAfterDelete = ApiClient.get("/pet/" + petId);
        assertEquals(404, getResponseAfterDelete.getStatusCode(), "Питомец не был удален");
    }
    
    @Test
    @Description("Попытка удаления несуществующего питомца")
    public void testDeleteNonExistingPet() {
        Response response = ApiClient.delete("/pet/999999999");
        assertEquals(404, response.getStatusCode(), "Ожидается код 404 для несуществующего питомца");
    }
}