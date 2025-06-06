package user;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.User;
import utils.ApiClient;

// Тесты для создания пользователей в системе
@Epic("Pet Store API")
@Feature("User Management")
public class UserCreateTest {
    
    // Инициализация тестового окружения
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }
    
    // Тест создания пользователя с валидными данными
    @Test
    @Description("Создание пользователя с корректными данными")
    public void testCreateUserWithValidData() {
        User user = new User(
            "testuser",
            "Test",
            "User",
            "test@example.com",
            "password123"
        );
        
        Response response = ApiClient.post("/user", user);
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
    }
    
    // Тест создания нескольких пользователей одним запросом
    @Test
    @Description("Создание нескольких пользователей одним запросом")
    public void testCreateUsersWithArray() {
        List<User> users = Arrays.asList(
            new User("user1", "First", "User", "user1@example.com", "pass1"),
            new User("user2", "Second", "User", "user2@example.com", "pass2")
        );
        
        Response response = ApiClient.post("/user/createWithArray", users);
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
    }
    
    // Тест создания пользователя с некорректным email
    @Test
    @Description("Создание пользователя с некорректным email")
    public void testCreateUserWithInvalidEmail() {
        User user = new User(
            "testuser",
            "Test",
            "User",
            "invalid-email",
            "password123"
        );
        
        Response response = ApiClient.post("/user", user);
        assertEquals(400, response.getStatusCode(), 
            "Ожидается код 400 при создании пользователя с некорректным email");
    }
}