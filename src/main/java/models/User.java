package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель пользователя магазина.
 * Содержит информацию о пользователе системы.
 */
public class User {
    // Уникальный идентификатор пользователя
    @JsonProperty
    private Long id;
    
    // Имя пользователя (логин)
    @JsonProperty
    private String username;
    
    // Имя пользователя
    @JsonProperty
    private String firstName;
    
    // Фамилия пользователя
    @JsonProperty
    private String lastName;
    
    // Email пользователя
    @JsonProperty
    private String email;
    
    // Пароль пользователя
    @JsonProperty
    private String password;
    
    // Телефон пользователя
    @JsonProperty
    private String phone;
    
    // Статус пользователя
    @JsonProperty
    private Integer userStatus;

    // Конструктор по умолчанию
    public User() {}

     /**
     * Создает нового пользователя с указанными параметрами.
     * 
     * @param username имя пользователя (логин)
     * @param firstName имя
     * @param lastName фамилия
     * @param email email
     * @param password пароль
     */

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}