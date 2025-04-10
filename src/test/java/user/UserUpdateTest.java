package user;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Pet Store API")
@Feature("User Management")
public class UserUpdateTest {
    
    private User testUser;
    
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    @BeforeEach
    public void setUp() {
        // Создаем тестового пользователя
        testUser = new User(
            "testuser",
            "Test",
            "User",
            "test@example.com",
            "password123"
        );
        
        Response createResponse = ApiClient.post("/user", testUser);
        assertEquals(200, createResponse.getStatusCode(), 
            "Не удалось создать тестового пользователя");
    }
    
    @Test
    @Description("Обновление данных существующего пользователя")
    public void testUpdateExistingUser() {
        // Обновляем данные пользователя
        testUser.setFirstName("Updated");
        testUser.setEmail("updated@example.com");
        
        Response response = ApiClient.put("/user/" + testUser.getUsername(), testUser);
        assertEquals(200, response.getStatusCode(), "Неверный статус код при обновлении");
        
        // Проверяем, что данные обновились
        Response getResponse = ApiClient.get("/user/" + testUser.getUsername());
        assertEquals(200, getResponse.getStatusCode(), "Неверный статус код при получении");
        
        User updatedUser = getResponse.as(User.class);
        assertEquals("Updated", updatedUser.getFirstName(), "Имя пользователя не обновилось");
        assertEquals("updated@example.com", updatedUser.getEmail(), "Email пользователя не обновился");
    }
    
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
        assertEquals(404, response.getStatusCode(), 
            "Ожидается код 404 при обновлении несуществующего пользователя");
    }
}