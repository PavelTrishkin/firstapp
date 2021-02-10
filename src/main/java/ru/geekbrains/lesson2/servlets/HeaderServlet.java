package ru.geekbrains.lesson2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_header")
public class HeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = (String) req.getAttribute("pageHeader");
        resp.getWriter().println("<h1>" + header + "</h1>");
        resp.getWriter().println("<a href=\"/firstapp/main\"> Main</a>");
        resp.getWriter().println("<a href=\"/firstapp/catalog\"> Catalog</a>");
        resp.getWriter().println("<a href=\"/firstapp/product\"> Products</a>");
        resp.getWriter().println("<a href=\"/firstapp/cart\"> Cart</a>");
        resp.getWriter().println("<a href=\"/firstapp/order\"> Order</a>");
    }
}
