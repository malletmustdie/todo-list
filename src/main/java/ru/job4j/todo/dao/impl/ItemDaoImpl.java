package ru.job4j.todo.dao.impl;

import java.util.List;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.model.Item;

public class ItemDaoImpl extends AbstractDao implements ItemDao {

    @Override
    public Item save(Item item) {
        this.tx(session -> session.save(item));
        return item;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete Item where id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public void update(Item item) {
        this.tx(session -> session.createQuery("update Item set done = : done where id = :id")
                                  .setParameter("done", item.getDone())
                                  .setParameter("id", item.getId())
                                  .executeUpdate());
    }

    @Override
    public Item findById(long id) {
        return (Item) this.tx(
                session -> session
                        .createQuery("from Item where id = :id")
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session
                        .createQuery("from Item")
                        .list()
        );
    }

    @Override
    public List<Item> findAllUnresolved() {
        return this.tx(
                session -> session
                        .createQuery("from Item i where i.done = false order by i.id")
                        .list()
        );
    }

}