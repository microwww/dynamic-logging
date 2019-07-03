package com.github.microwww.dylog.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;
import java.util.logging.Logger;

@HandlesTypes(value = {ServletMapping.class})
public class LogServletContainerInitializer implements ServletContainerInitializer {

    private static final String PATH_KEY = LogServletContainerInitializer.class.getName() + ".path";

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        Logger.getLogger(LogServletContainerInitializer.class.getName()).info("Init LogServletContainerInitializer");
        ServletRegistration.Dynamic servlet = ctx.addServlet(LogServletContainerInitializer.class.getName(), new ServletMapping());
        String path = System.getProperty(PATH_KEY);
        if (path == null || path.trim().length() == 0) {
            path = System.getenv(PATH_KEY);
            if (path == null || path.trim().length() == 0) {
                path = "/change-log-level";
            }
        }
        servlet.addMapping(path);
    }
}