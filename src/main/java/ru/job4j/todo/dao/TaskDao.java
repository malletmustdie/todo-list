package ru.job4j.todo.dao;

import java.util.List;

import ru.job4j.todo.model.Task;

public interface TaskDao {

    Task save(Task item);

    void delete(Long id);

    void update(Task item);

    Task findById(Long id);

    List<Task> findAll();

    List<Task> findAllUnresolved();

}
