package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.User;

public class CurrentUserServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object user = req.getSession().getAttribute("user");
        String response = GSON.toJson(user);
        if (user == null) {
            response = GSON.toJson(new User("Unregistered user"));
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
    }

}
