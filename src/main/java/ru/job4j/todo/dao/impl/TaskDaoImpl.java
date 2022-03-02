package ru.job4j.todo.dao.impl;

import java.util.List;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.TaskDao;
import ru.job4j.todo.model.Task;

public class TaskDaoImpl extends AbstractDao implements TaskDao {

    @Override
    public Task save(Task item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete Task where id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public void update(Task item) {
        this.tx(session -> session.createQuery("update Task set done = : done where id = :id")
                                  .setParameter("done", item.getDone())
                                  .setParameter("id", item.getId())
                                  .executeUpdate());
    }

    @Override
    public Task findById(long id) {
        return (Task) this.tx(
                session -> session
                        .createQuery("from Task where id = :id")
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    @Override
    public List<Task> findAll() {
        return this.tx(
                session -> session
                        .createQuery("from Task")
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