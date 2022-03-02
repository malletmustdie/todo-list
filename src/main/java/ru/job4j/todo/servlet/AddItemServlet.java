package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.dao.ItemDao;
import ru.job4j.todo.dao.impl.ItemDaoImpl;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

public class AddItemServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    private final ItemDao itemDao = new ItemDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("desc");
        User user = (User) req.getSession().getAttribute("user");
        Task item = new Task(description);
        item.setUser(user);
        itemDao.save(item);
        String response = GSON.toJson(item);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
    }

}
