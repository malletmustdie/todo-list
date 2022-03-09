package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.todo.dao.TaskDao;
import ru.job4j.todo.dao.impl.CategoryDaoImpl;
import ru.job4j.todo.dao.impl.TaskDaoImpl;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

public class AddTaskServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        JsonObject requestData = new Gson().fromJson(req.getReader(), JsonObject.class);
        String description = requestData.get("desc").getAsString();
        List<String> cIds = getCategoriesIDsList(requestData.get("cIds").getAsJsonArray());
        TaskDao taskDao = TaskDaoImpl.getTaskDao();
        Task task = new Task(description);
        task.setUser(user);
        setCategoryToTask(task, cIds);
        taskDao.save(task);
        String response = GSON.toJson(task);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
    }

    private void setCategoryToTask(Task task, List<String> ids) {
        ids.stream()
           .map(id -> CategoryDaoImpl.getCategoryDao().findById(Long.parseLong(id)))
           .forEachOrdered(task::addCategory);
    }

    private List<String> getCategoriesIDsList(JsonArray array) {
        return IntStream.range(0, array.size())
                        .mapToObj(i -> array.get(i).getAsString())
                        .collect(Collectors.toList());
    }

}
