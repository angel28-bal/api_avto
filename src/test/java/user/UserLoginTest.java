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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Pet Store API")
@Feature("User Management")
public class UserLoginTest {
    
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "password123";
    
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    @BeforeEach
    public void setUp() {
        // Создаем тестового пользователя
        User user = new User(
            TEST_USERNAME,
            "Test",
            "User",
            "test@example.com",
            TEST_PASSWORD
        );
        
        Response createResponse = ApiClient.post("/user", user);
        assertEquals(200, createResponse.getStatusCode(), 
            "Не удалось создать тестового пользователя");
    }
    
    @Test
    @Description("Успешная авторизация пользователя")
    public void testSuccessfulLogin() {
        Response response = ApiClient.get(String.format("/user/login?username=%s&password=%s", 
            TEST_USERNAME, TEST_PASSWORD));
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        assertNotNull(response.getHeader("X-Rate-Limit"), 
            "Отсутствует заголовок X-Rate-Limit");
        assertNotNull(response.getHeader("X-Expires-After"), 
            "Отсутствует заголовок X-Expires-After");
    }
    
    @Test
    @Description("Попытка авторизации с неверным паролем")
    public void testLoginWithInvalidPassword() {
        Response response = ApiClient.get(String.format("/user/login?username=%s&password=%s", 
            TEST_USERNAME, "wrongpassword"));
        
        assertEquals(400, response.getStatusCode(), 
            "Ожидается код 400 при попытке входа с неверным паролем");
    }
    
    @Test
    @Description("Выход пользователя из системы")
    public void testLogout() {
        // Сначала выполняем вход
        Response loginResponse = ApiClient.get(String.format("/user/login?username=%s&password=%s", 
            TEST_USERNAME, TEST_PASSWORD));
        assertEquals(200, loginResponse.getStatusCode(), "Не удалось войти в систему");
        
        // Затем выполняем выход
        Response logoutResponse = ApiClient.get("/user/logout");
        assertEquals(200, logoutResponse.getStatusCode(), 
            "Неверный статус код при выходе из системы");
    }
}