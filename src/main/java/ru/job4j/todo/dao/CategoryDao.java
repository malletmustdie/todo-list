package ru.job4j.todo.dao;

import java.util.List;

import ru.job4j.todo.model.Category;

public interface CategoryDao {

    Category findById(Long id);

    List<Category> findAll();

}
