package ru.job4j.todo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import ru.job4j.todo.model.User;
import ru.job4j.todo.dao.UserDao;
import ru.job4j.todo.dao.impl.UserDaoImpl;

public class RegServlet extends HttpServlet {

    private final UserDao store = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        if (store.findByEmail(email) != null) {
            req.setAttribute(
                    "error",
                    String.format("Пользователь с email: %s уже зарегистрирован.", email)
            );
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            store.save(new User(req.getParameter("name"), email, req.getParameter("password")));
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }

    }

}
