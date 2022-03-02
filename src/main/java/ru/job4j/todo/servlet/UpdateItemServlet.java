package ru.job4j.todo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;
import ru.job4j.todo.model.Item;

public class UpdateItemServlet extends HttpServlet {

    private final ItemDao store = new ItemDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Item item = store.findById(Long.parseLong(id));
        item.setId(Long.parseLong(id));
        item.setDone(true);
        store.update(item);
    }

}
