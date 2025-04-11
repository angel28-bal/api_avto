Команды для запуска тестов:

1. Установка зависимостей:
mvn clean install

2. Запуск всех тестов:
mvn clean test

3. Запуск конкретного тестового класса:
mvn test -Dtest=PetCreateTest

4. Генерация отчета Allure:
allure generate target/allure-results -o target/allure-report

5. Открытие отчета в браузере:
allure open target/allure-report

6.Также прилржен скрин с aller отчетом.

Описание автотестов PetStore API

Тесты авторизации (auth/)

AuthTest.java
- testValidApiKey() - проверяет доступ с валидным API ключом "special-key"
- testWithoutApiKey() - проверяет доступ без API ключа (ожидается 401)
- testInvalidApiKey() - проверяет доступ с неверным API ключом (ожидается 401)

Тесты заказов (order/)

OrderCreateTest.java
- testCreateOrder() - создание нового заказа
- testCreateOrderWithInvalidPetId() - создание заказа с несуществующим ID питомца (ожидается 400)
- testCreateOrderWithInvalidQuantity() - создание заказа с отрицательным количеством (ожидается 400)

OrderDeleteTest.java
- testDeleteExistingOrder() - удаление существующего заказа
- testDeleteNonExistingOrder() - удаление несуществующего заказа (ожидается 404)

OrderGetTest.java
- testGetExistingOrder() - получение информации о существующем заказе
- testGetNonExistingOrder() - получение информации о несуществующем заказе (ожидается 404)

Тесты питомцев (pet/)

PetCreateTest.java
- testCreatePet() - создание нового питомца
- testCreatePetWithInvalidData() - создание питомца с некорректными данными

PetDeleteTest.java
- testDeleteExistingPet() - удаление существующего питомца
- testDeleteNonExistingPet() - удаление несуществующего питомца (ожидается 404)

PetGetTest.java
- testGetExistingPet() - получение информации о существующем питомце
- testGetNonExistingPet() - получение информации о несуществующем питомце (ожидается 404)

PetUpdateTest.java
- testUpdateExistingPet() - обновление информации о существующем питомце
- testUpdateNonExistingPet() - обновление информации о несуществующем питомце (ожидается 404)

Тесты пользователей (user/)

UserCreateTest.java
- testCreateUser() - создание нового пользователя
- testCreateUserWithInvalidEmail() - создание пользователя с некорректным email (ожидается 400)

UserGetTest.java
- testGetExistingUser() - получение информации о существующем пользователе
- testGetNonExistingUser() - получение информации о несуществующем пользователе (ожидается 404)

UserLoginTest.java
- testLoginWithValidCredentials() - вход с валидными учетными данными
- testLoginWithInvalidCredentials() - вход с неверными учетными данными (ожидается 400)

UserUpdateTest.java
- testUpdateExistingUser() - обновление информации о существующем пользователе
- testUpdateNonExistingUser() - обновление информации о несуществующем пользователе (ожидается 404)