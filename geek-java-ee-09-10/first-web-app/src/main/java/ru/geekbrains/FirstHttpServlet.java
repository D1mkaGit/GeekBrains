package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*

        ""               /

       /something       /something

       /something/*     /something/sdfsdfds
                        /something/sdfsdfds/dfsdfdsf
                        /something

       *.jpeg           /fdsfsfd/sdfsdfsf/sdsd.jpeg
                        /fdfdfdf.jpeg

       /*               /sdasda/d/asd/a/ds/asdasd

       /                /sdasda/d/asd/a/ds/asdasd

 */

//  /sdfsdfsd/*.jpeg

// http://localhost:8080/first-web-app/ghghgh/hjhjhj/index.html#sdfsdfs?param1=value&param2=value

// GET /first-web-app/index.html HTTP/1.1

@WebServlet(urlPatterns = {"/http_servlet/*"})
public class FirstHttpServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>First HTTP Servlet</h1>");
        resp.getWriter().println("Привет!!!");

        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        for (Product prod : productRepository.findAll()) {
            resp.getWriter().println("<p>" + prod.getName() + "</p>");
        }
    }
}
