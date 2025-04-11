package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

// Тесты для удаления заказов
@Epic("Pet Store API")
@Feature("Store Management")
public class OrderDeleteTest {
    
    private Long orderId;
    
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
        Order order = new Order(petId, 1, "placed");
        Response orderResponse = ApiClient.post("/store/order", order);
        assertEquals(200, orderResponse.getStatusCode(), "Не удалось создать тестовый заказ");
        
        orderId = orderResponse.as(Order.class).getId();
    }
    
    // Тест удаления существующего заказа
    @Test
    @Description("Удаление существующего заказа")
    public void testDeleteExistingOrder() {
        // Удаляем заказ
        Response deleteResponse = ApiClient.delete("/store/order/" + orderId);
        assertEquals(200, deleteResponse.getStatusCode(), "Неверный статус код при удалении");
        
        // Проверяем, что заказ действительно удален
        Response getResponse = ApiClient.get("/store/order/" + orderId);
        assertEquals(404, getResponse.getStatusCode(), 
            "Заказ все еще доступен после удаления");
    }
    
    // Тест попытки удаления несуществующего заказа
    @Test
    @Description("Попытка удаления несуществующего заказа")
    public void testDeleteNonExistingOrder() {
        Response response = ApiClient.delete("/store/order/999999999");
        assertEquals(404, response.getStatusCode(), 
            "Ожидается код 404 при попытке удаления несуществующего заказа");
    }
}