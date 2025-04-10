package pet;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.Category;
import models.Pet;
import models.Tag;
import utils.ApiClient;

@Epic("Pet Store API")
@Feature("Pet Management")
public class PetCreateTest {

    @Test
    @Description("Создание питомца с корректными данными")
    public void testCreatePetWithValidData() {
        // Подготовка тестовых данных
        Pet pet = new Pet("Doggie", "available");
        pet.setCategory(new Category(1L, "Dogs"));
        pet.setPhotoUrls(Collections.singletonList("http://example.com/dog.jpg"));
        pet.setTags(Arrays.asList(new Tag(1L, "friendly"), new Tag(2L, "trained")));

        // Отправка запроса
        Response response = ApiClient.post("/pet", pet);

        // Проверки
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        
        Pet createdPet = response.as(Pet.class);
        assertNotNull(createdPet.getId(), "ID питомца не должен быть null");
        assertEquals(pet.getName(), createdPet.getName(), "Имя питомца не совпадает");
        assertEquals(pet.getStatus(), createdPet.getStatus(), "Статус питомца не совпадает");
    }

    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    @Description("Создание питомца с разными статусами")
    public void testCreatePetWithDifferentStatuses(String status) {
        Pet pet = new Pet("TestPet", status);
        Response response = ApiClient.post("/pet", pet);
        
        assertEquals(200, response.getStatusCode(), "Неверный статус код");
        Pet createdPet = response.as(Pet.class);
        assertEquals(status, createdPet.getStatus(), "Статус питомца не совпадает");
    }

    @Test
    @Description("Негативный тест: создание питомца без обязательных полей")
    public void testCreatePetWithoutRequiredFields() {
        Pet pet = new Pet();
        Response response = ApiClient.post("/pet", pet);
        
        assertEquals(405, response.getStatusCode(), "Ожидается код ошибки 405");
    }
} 