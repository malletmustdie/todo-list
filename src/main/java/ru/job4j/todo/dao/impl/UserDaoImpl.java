package ru.job4j.todo.dao.impl;

import java.util.Collection;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.UserDao;
import ru.job4j.todo.model.User;

public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public User save(User user) {
        this.tx(session -> session.save(user));
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return (User) this.tx(
                session -> session
                        .createQuery("FROM User WHERE email = :email")
                        .setParameter("email", email)
                        .uniqueResult()
        );
    }

    @Override
    public Collection<User> findAll() {
        return this.tx(
                session -> session
                        .createQuery("FROM User")
                        .list()
        );
    }

}
