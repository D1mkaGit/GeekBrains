package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstServlet implements Servlet {

    private final Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    private ServletConfig config;

    private final AtomicInteger cnt = new AtomicInteger(0);

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request {}" , cnt.incrementAndGet());

        servletResponse.getWriter().println("<h1>Hello from Servlet!!!</h1>");
        servletResponse.getWriter().println("<h2>!!!</h2>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
