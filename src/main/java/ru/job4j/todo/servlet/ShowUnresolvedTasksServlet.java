package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.dao.TaskDao;
import ru.job4j.todo.dao.impl.TaskDaoImpl;
import ru.job4j.todo.model.Task;

public class ShowUnresolvedTasksServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskDao taskDao = TaskDaoImpl.getTaskDao();
        List<Task> list = taskDao.findAllUnresolved();
        String json = GSON.toJson(list);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(json);
    }

}
