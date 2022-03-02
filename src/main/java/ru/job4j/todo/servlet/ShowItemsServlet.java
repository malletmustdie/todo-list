package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;

public class ShowItemsServlet extends HttpServlet {

    private final ItemDao store = new ItemDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var list = store.findAll();
        String json = new Gson().toJson(list);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }

}
