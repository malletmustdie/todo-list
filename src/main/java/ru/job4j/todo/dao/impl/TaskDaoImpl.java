package ru.job4j.todo.dao.impl;

import java.util.List;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.TaskDao;
import ru.job4j.todo.model.Task;

public class TaskDaoImpl extends AbstractDao implements TaskDao {

    private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();

    public static TaskDaoImpl getTaskDao() {
        return INSTANCE;
    }

    @Override
    public Task save(Task item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete Task i where i.id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public void updateTaskStatus(Long id) {
        this.tx(session -> session.createQuery("update Task i set i.done = true where i.id = :id")
                                  .setParameter("id", id)
                                  .executeUpdate());
    }

    @Override
    public List<Task> findAll() {
        return this.tx(
                session -> session
                        .createQuery("from Task i order by i.id")
                        .list()
        );
    }

    @Override
    public List<Task> findAllUnresolved() {
        return this.tx(
                session -> session
                        .createQuery("from Task i where i.done = false order by i.id")
                        .list()
        );
    }

}