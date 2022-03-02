package ru.job4j.todo.dao;

import java.util.List;

import ru.job4j.todo.model.Item;

public interface ItemDao {

    Item save(Item item);

    void delete(Long id);

    void update(Item item);

    Item findById(long id);

    List<Item> findAll();

    List<Item> findAllUnresolved();

}
