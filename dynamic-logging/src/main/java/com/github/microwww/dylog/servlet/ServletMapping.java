package com.github.microwww.dylog.servlet;

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

@WebServlet(urlPatterns = "/change-log-level")
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
            try {
                // log4j 1.x.x
                Class<?> clazz = Class.forName("org.apache.log4j.Level");
                Log4jone.changeLevel(logger, level);
                success.add("log4j 1.x.x");
            } catch (IllegalArgumentException e) {
                fails.add("log4j 1.x.x ," + e.getMessage());
            } catch (ClassNotFoundException e) {// ignore
            }
            try {
                //log4j 2.x.x
                Class<?> clazz = Class.forName("org.apache.logging.log4j.core.config.Configurator");
                Log4jtwo.changeLevel(logger, level);
                success.add("log4j 2.x.x");
            } catch (IllegalArgumentException e) {
                fails.add("log4j 2.x.x ," + e.getMessage());
            } catch (ClassNotFoundException ex) {// ignore
            }
            try {
                //logback
                Class<?> clazz = Class.forName("ch.qos.logback.classic.LoggerContext");
                Logback.changeLevel(logger, level);
                success.add("logback");
            } catch (IllegalArgumentException e) {
                fails.add("logback , " + e.getMessage());
            } catch (ClassNotFoundException ex) {// ignore
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
