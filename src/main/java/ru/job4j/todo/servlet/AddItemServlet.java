package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;
import ru.job4j.todo.model.User;

public class AddItemServlet extends HttpServlet {

    private final ItemDao store = new ItemDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("desc");
        User user = (User) req.getSession().getAttribute("user");
        Item item = new Item(description);
        item.setUser(user);
        store.save(item);
        String json = new Gson().toJson(item);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }

}
