package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.dao.TaskDao;
import ru.job4j.todo.dao.impl.TaskDaoImpl;
import ru.job4j.todo.model.Task;

public class UpdateTaskStatusServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    private final TaskDao store = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Task item = store.findById(Long.parseLong(id));
        item.setId(Long.parseLong(id));
        item.setDone(true);
        store.update(item);
        String json = GSON.toJson("200 OK");
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(json);
    }

}
