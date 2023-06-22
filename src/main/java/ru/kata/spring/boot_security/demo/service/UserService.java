package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService{
    void add(User user);
    List<User> listUsers();
    User show(int id);
    void update(User updatedUser);
    void delete(int id);
    User findUsersByEmail(String username);
}