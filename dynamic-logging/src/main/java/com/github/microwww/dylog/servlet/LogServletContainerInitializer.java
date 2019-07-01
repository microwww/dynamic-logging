package com.github.microwww.dylog.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;
import java.util.logging.Logger;

@HandlesTypes(value={ServletMapping.class})
public class LogServletContainerInitializer implements ServletContainerInitializer {

    // TODO not using !! why ???
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        Logger.getLogger(LogServletContainerInitializer.class.getName()).info("Init LogServletContainerInitializer");
        ServletRegistration.Dynamic servlet = ctx.addServlet("change-log-level-ServletMapping", new ServletMapping());
        servlet.addMapping("/change-log-level");
    }
}