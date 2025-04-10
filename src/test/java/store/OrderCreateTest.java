package store;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.Order;
import models.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiClient;
import utils.TestDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Pet Store API")
@Feature("Store Management")
public class OrderCreateTest {
    
    private Long petId;
    
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    @BeforeEach
    public void setUp() {
        // Создаем питомца для заказа
        Pet testPet = TestDataGenerator.generateRandomPet();
        Response createResponse = ApiClient.post("/pet", testPet);
        assertEquals(200, createResponse.getStatusCode(), "Не удалось создать тестового питомца");
        
        petId = createResponse.as(Pet.class).getId();
    }
    
    @Test
    @Description("Создание заказа с корректными данными")
    public void testCreateOrderWithValidData() {
        // Создаем заказ
        Order order = new Order(petId, 1, "placed");
        Response response = ApiClient.post("/store/order", order);
        
        // Проверяем результат
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        Order createdOrder = response.as(Order.class);
        assertNotNull(createdOrder.getId(), "ID заказа не должен быть null");
        assertEquals(petId, createdOrder.getPetId(), "ID питомца не совпадает");
        assertEquals("placed", createdOrder.getStatus(), "Статус заказа не совпадает");
    }
    
    @Test
    @Description("Создание заказа с некорректным ID питомца")
    public void testCreateOrderWithInvalidPetId() {
        Order order = new Order(999999999L, 1, "placed");
        Response response = ApiClient.post("/store/order", order);
        
        assertEquals(400, response.getStatusCode(), 
            "Ожидается код 400 при создании заказа с несуществующим питомцем");
    }
    
    @Test
    @Description("Создание заказа с некорректным количеством")
    public void testCreateOrderWithInvalidQuantity() {
        Order order = new Order(petId, -1, "placed");
        Response response = ApiClient.post("/store/order", order);
        
        assertEquals(400, response.getStatusCode(), 
            "Ожидается код 400 при создании заказа с отрицательным количеством");
    }
}