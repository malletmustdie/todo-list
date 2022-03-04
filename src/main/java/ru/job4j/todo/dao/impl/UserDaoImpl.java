package ru.job4j.todo.dao.impl;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.UserDao;
import ru.job4j.todo.model.User;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private UserDaoImpl() {

    }

    private static class Holder {
        public static final UserDaoImpl HOLDER_INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getUserDao() {
        return Holder.HOLDER_INSTANCE;
    }

    @Override
    public User save(User user) {
        this.tx(session -> session.save(user));
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return (User) this.tx(
                session -> session
                        .createQuery("from User where email = :email")
                        .setParameter("email", email)
                        .uniqueResult()
        );
    }

}
