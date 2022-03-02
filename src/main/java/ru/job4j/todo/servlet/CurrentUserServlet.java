package ru.job4j.todo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.google.gson.Gson;
import ru.job4j.todo.model.User;

public class CurrentUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object user = req.getSession().getAttribute("user");
        String json = new Gson().toJson(new User("Unregistered user", "", ""));
        if (user != null) {
            json = new Gson().toJson(user);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(json);
    }

}
