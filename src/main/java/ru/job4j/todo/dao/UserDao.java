package ru.job4j.todo.dao;

import ru.job4j.todo.model.User;

public interface UserDao {

    User save(User user);

    User findByEmail(String email);

}
