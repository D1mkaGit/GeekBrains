package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/product/*")
public class ProductControllerServlet extends HttpServlet {

    private static final Pattern pathParam = Pattern.compile("/(\\d*)$"); // \1231231

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
        }
        if (req.getPathInfo().equals("/new")) {
            // TODO
            getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
        } else {
            Matcher matcher = pathParam.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id;
                try {
                    id = Long.parseLong(matcher.group(1));
                } catch (NumberFormatException ex) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                req.setAttribute("product", productRepository.findById(id)
                        .orElseThrow(NotFoundException::new));
                getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            String strId = req.getParameter("id");
            try {
                Long id = null;
                if (!strId.equals("")) id = Long.parseLong(strId);
                Product product = new Product(id,
                        req.getParameter("name"),
                        new BigDecimal(req.getParameter("price")));
                productRepository.save(product);
                resp.sendRedirect(getServletContext().getContextPath() + "/product");
            } catch (NumberFormatException ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
