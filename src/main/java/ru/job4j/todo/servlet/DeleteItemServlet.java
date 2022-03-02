package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;

public class DeleteItemServlet extends HttpServlet {

    private final ItemDao store = new ItemDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        store.delete(Long.valueOf(id));
    }

}
