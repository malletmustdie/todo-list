package ru.job4j.todo.dao;

import java.util.List;

import ru.job4j.todo.model.Task;

public interface ItemDao {

    Task save(Task item);

    void delete(Long id);

    void update(Task item);

    Task findById(long id);

    List<Task> findAll();

    List<Task> findAllUnresolved();

}
