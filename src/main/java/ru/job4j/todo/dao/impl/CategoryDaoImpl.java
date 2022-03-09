package ru.job4j.todo.dao.impl;

import java.util.List;

import ru.job4j.todo.dao.AbstractDao;
import ru.job4j.todo.dao.CategoryDao;
import ru.job4j.todo.model.Category;

public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

    private CategoryDaoImpl() {

    }

    private static class Holder {
        private static final CategoryDaoImpl HOLDER_INSTANCE = new CategoryDaoImpl();
    }

    public static CategoryDaoImpl getCategoryDao() {
        return Holder.HOLDER_INSTANCE;
    }

    @Override
    public Category findById(Long id) {
        return (Category) this.tx(
                session -> session
                        .createQuery("from Category c where c.id = :id")
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    @Override
    public List<Category> findAll() {
        return this.tx(
                session -> session
                        .createQuery("from Category c order by c.id")
                        .list()
        );
    }

}
