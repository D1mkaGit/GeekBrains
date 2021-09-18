package ru.geekbrains.dz2_servlets.my;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/myDZ2_Products/*"})
public class Products extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.getWriter().println("<p>requestUrl: " + req.getRequestURL() + "</p>");
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        String getContextAndServletPath = req.getContextPath() + req.getServletPath();
        if (req.getPathInfo() != null && !req.getPathInfo().equals("/") && isNumber(req.getPathInfo().replace("/", ""))) {
            Optional<Product> productFromPathInfo = productRepository.findById(Long.parseLong(req.getPathInfo().replace("/", "")));

            if (productFromPathInfo.isPresent()) {
                resp.getWriter().println("<h1>Product Details</h1>");
                resp.getWriter().println("<p>Product Id: " + productFromPathInfo.get().getId() + "</p>");
                resp.getWriter().println("<p>Product Name: " + productFromPathInfo.get().getName() + "</p>");
                resp.getWriter().println("<p>Product Price: " + productFromPathInfo.get().getPrice() + "</p>");

                resp.getWriter().println("<p><a href=\"" + getContextAndServletPath + "\">To Products List</a></p>");
            }

        } else {
            resp.getWriter().println("<h1>Product List</h1>");
            for (Product prod : productRepository.findAll()) {
                resp.getWriter().println("<p><a href=\""
                        + getContextAndServletPath +
                        "/" + prod.getId() + "\">" + prod.getName() + "</a></p>");
            }
        }
    }

    private boolean isNumber(String score) {
        return score.matches("^\\d+$");
    }
}
