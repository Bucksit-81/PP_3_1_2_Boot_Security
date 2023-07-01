package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> getall();
    void addUser(User user);
    User getUser(long id);
    void deleteUser(long id);
    void updateUser(User user,long id);

    User getUserByName(String username);


}