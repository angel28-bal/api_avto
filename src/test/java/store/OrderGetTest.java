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
public class OrderGetTest {
    
    private Long orderId;
    private Order testOrder;
    
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    @BeforeEach
    public void setUp() {
        // Создаем питомца для заказа
        Pet testPet = TestDataGenerator.generateRandomPet();
        Response petResponse = ApiClient.post("/pet", testPet);
        Long petId = petResponse.as(Pet.class).getId();
        
        // Создаем тестовый заказ
        testOrder = new Order(petId, 1, "placed");
        Response orderResponse = ApiClient.post("/store/order", testOrder);
        assertEquals(200, orderResponse.getStatusCode(), "Не удалось создать тестовый заказ");
        
        orderId = orderResponse.as(Order.class).getId();
    }
    
    @Test
    @Description("Получение существующего заказа по ID")
    public void testGetExistingOrder() {
        Response response = ApiClient.get("/store/order/" + orderId);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        Order retrievedOrder = response.as(Order.class);
        assertNotNull(retrievedOrder, "Полученный заказ не должен быть null");
        assertEquals(orderId, retrievedOrder.getId(), "ID заказа не совпадает");
        assertEquals(testOrder.getPetId(), retrievedOrder.getPetId(), "ID питомца не совпадает");
        assertEquals(testOrder.getStatus(), retrievedOrder.getStatus(), "Статус заказа не совпадает");
    }
    
    @Test
    @Description("Попытка получения несуществующего заказа")
    public void testGetNonExistingOrder() {
        Response response = ApiClient.get("/store/order/999999999");
        
        assertEquals(404, response.getStatusCode(), 
            "Ожидается код 404 при запросе несуществующего заказа");
    }
    
    @Test
    @Description("Получение информации об инвентаре магазина")
    public void testGetInventory() {
        Response response = ApiClient.get("/store/inventory");
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        // Проверяем, что ответ содержит информацию о количестве питомцев по статусам
        assertNotNull(response.jsonPath().get("available"), 
            "Отсутствует информация о доступных питомцах");
    }
}