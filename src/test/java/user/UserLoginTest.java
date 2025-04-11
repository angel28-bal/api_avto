package user;

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
import models.User;
import utils.ApiClient;

// Тесты для авторизации пользователей
@Epic("Pet Store API")
@Feature("User Management")
public class UserLoginTest {
    
    private User testUser;
    private String username;
    private String password;
    
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
        password = testUser.getPassword();
    }
    
    // Тест успешной авторизации с валидными данными
    @Test
    @Description("Успешная авторизация пользователя")
    public void testLoginWithValidCredentials() {
        Response response = ApiClient.get("/user/login?username=" + username + "&password=" + password);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        String sessionId = response.getCookie("sessionId");
        assertNotNull(sessionId, "Сессионный ID не должен быть null");
    }
    
    // Тест попытки авторизации с неверными учетными данными
    @Test
    @Description("Попытка авторизации с неверными учетными данными")
    public void testLoginWithInvalidCredentials() {
        Response response = ApiClient.get("/user/login?username=invalid&password=invalid");
        
        assertEquals(400, response.getStatusCode(), "Ожидается код 400 при неверных учетных данных");
    }
    
    // Тест выхода пользователя из системы
    @Test
    @Description("Выход пользователя из системы")
    public void testLogout() {
        Response response = ApiClient.get("/user/logout");
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
    }
}