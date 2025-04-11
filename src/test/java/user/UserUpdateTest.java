package user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.User;
import utils.ApiClient;

// Тесты для обновления данных пользователей
@Epic("Pet Store API")
@Feature("User Management")
public class UserUpdateTest {
    
    private User testUser;
    private String username;
    
    // Инициализация тестового окружения
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    // Подготовка тестовых данных перед каждым тестом
    @BeforeEach
    public void setUp() {
        testUser = new User("testuser", "Test", "User", "test@example.com", "password123");
        Response createResponse = ApiClient.post("/user", testUser);
        assertEquals(200, createResponse.getStatusCode(), "Не удалось создать тестового пользователя");
        
        username = testUser.getUsername();
    }
    
    // Тест обновления существующего пользователя
    @Test
    @Description("Обновление данных существующего пользователя")
    public void testUpdateExistingUser() {
        testUser.setFirstName("UpdatedFirst");
        testUser.setLastName("UpdatedLast");
        testUser.setEmail("updated@example.com");
        
        Response response = ApiClient.put("/user/" + username, testUser);
        assertEquals(200, response.getStatusCode(), "Неверный статус код при обновлении пользователя");
        
        Response getResponse = ApiClient.get("/user/" + username);
        assertEquals(200, getResponse.getStatusCode(), "Не удалось получить обновленного пользователя");
    }
    
    // Тест попытки обновления несуществующего пользователя
    @Test
    @Description("Попытка обновления несуществующего пользователя")
    public void testUpdateNonExistingUser() {
        User nonExistingUser = new User(
            "nonexistent",
            "Non",
            "Existent",
            "non@example.com",
            "password"
        );
        
        Response response = ApiClient.put("/user/nonexistent", nonExistingUser);
        assertEquals(404, response.getStatusCode(), "Ожидается код 404 при обновлении несуществующего пользователя");
    }
}