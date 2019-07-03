package com.github.microwww.dylog.servlet;

import com.github.microwww.dylog.ChangeLoggingLevel;
import com.github.microwww.dylog.Log4jone;
import com.github.microwww.dylog.Log4jtwo;
import com.github.microwww.dylog.Logback;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ServletMapping extends HttpServlet {

    private static Logger logger = Logger.getLogger(ServletMapping.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("Start change log-level, GET/POST : " + this.getServletContext().getContextPath() + " /change-log-level?logger=...&level=...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String logger = req.getParameter("logger");
        String level = req.getParameter("level");

        List<String> success = new ArrayList<>();
        List<String> fails = new ArrayList<>();
        resp.setContentType("text/plain; charset=ISO-8859-1");
        if (Objects.isNull(logger) || Objects.isNull(level)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Request error ! GET/POST logger=...&level=...");
        } else {
            ChangeLoggingLevel[] list = {new Log4jone(), new Log4jtwo(), new Logback()};
            for (ChangeLoggingLevel it : list) {
                try {
                    it.changeLevel(logger, level);
                    success.add("change logger " + it.getName());
                } catch (IllegalArgumentException e) {
                    fails.add("Arguments error : " + it.getName());
                } catch (UnsupportedOperationException e) { // ignore
                }
            }
        }
        if (success.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Setting FAIL , not match log-sys, check dependence, log4j OR log4j2 !");
            if (!fails.isEmpty()) {
                resp.getWriter().write(" ERROR: " + fails.toString());
            }
        } else {
            resp.getWriter().write("Setting SUCCESS : " + success.toString());
        }

    }
}
