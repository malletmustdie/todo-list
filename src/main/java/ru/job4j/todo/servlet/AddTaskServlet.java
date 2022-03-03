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
import ru.job4j.todo.model.User;

public class AddTaskServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("desc");
        User user = (User) req.getSession().getAttribute("user");
        TaskDao taskDao = TaskDaoImpl.getTaskDao();
        Task task = new Task(description);
        task.setUser(user);
        taskDao.save(task);
        String response = GSON.toJson(task);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
    }

}
