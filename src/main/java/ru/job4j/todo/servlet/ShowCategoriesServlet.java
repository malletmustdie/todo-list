package ru.job4j.todo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.dao.CategoryDao;
import ru.job4j.todo.dao.impl.CategoryDaoImpl;
import ru.job4j.todo.model.Category;

public class ShowCategoriesServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDao categoryDao = CategoryDaoImpl.getCategoryDao();
        List<Category> list = categoryDao.findAll();
        String json = GSON.toJson(list);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(json);
    }

}
