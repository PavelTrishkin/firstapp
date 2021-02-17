package ru.geekbrains.lesson2.servlets;

import ru.geekbrains.lesson2.persist.Category;
import ru.geekbrains.lesson2.persist.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/category/*")
public class CategoryServlet extends HttpServlet {

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (categoryRepository == null) {
            throw new ServletException("CategoryRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categories", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Category category = categoryRepository.findById(id);
            if (category == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            categoryRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
        } else if (req.getPathInfo().equals("/new")) {
            Category category = new Category();
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_new_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            if (req.getParameter("id") != null) {
                id = Long.parseLong(req.getParameter("id"));
            } else {
                id = null;
            }
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }

        Category category = new Category(id, req.getParameter("title"));
        categoryRepository.saveOrUpdate(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/category");
    }
}
