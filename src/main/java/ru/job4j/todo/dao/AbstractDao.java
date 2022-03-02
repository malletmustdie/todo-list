package ru.job4j.todo.dao;

import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class AbstractDao {

    private static final StandardServiceRegistry REGISTRY =
            new StandardServiceRegistryBuilder().configure().build();

    private static final SessionFactory SESSION_FACTORY =
            new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    protected  <T> T tx(final Function<Session, T> command) {
        final Session session = getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
