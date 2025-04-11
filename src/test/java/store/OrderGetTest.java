package store;

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
import models.Order;
import models.Pet;
import utils.ApiClient;
import utils.TestDataGenerator;

// Тесты для получения информации о заказах в магазине
@Epic("Pet Store API")
@Feature("Store Management")
public class OrderGetTest {
    
    private Long orderId;
    private Order testOrder;
    
    // Инициализация тестового окружения
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    // Подготовка тестовых данных перед каждым тестом
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
    
    // Тест получения существующего заказа по ID
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
    
    // Тест попытки получения несуществующего заказа
    @Test
    @Description("Попытка получения несуществующего заказа")
    public void testGetNonExistingOrder() {
        Response response = ApiClient.get("/store/order/999999999");
        
        assertEquals(404, response.getStatusCode(), 
            "Ожидается код 404 при запросе несуществующего заказа");
    }
    
    // Тест получения информации об инвентаре магазина
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