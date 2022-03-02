package ru.job4j.todo.dao;

import ru.job4j.todo.model.Item;

import java.util.Collection;

public interface ItemDao {

    Item save(Item item);

    void delete(Long id);

    void update(Item item);

    Item findById(long id);

    Collection<Item> findAll();

    Collection<Item> findAllUnresolved();

    Collection<Item> findAllDone();

    Collection<Item> findAllNotDone();

}
