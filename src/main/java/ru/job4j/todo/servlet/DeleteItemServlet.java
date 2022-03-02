package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;

public class DeleteItemServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    private final ItemDao store = new ItemDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        store.delete(Long.valueOf(id));
        String response = GSON.toJson("200 OK");
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
    }

}
