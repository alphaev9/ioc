package com.alpha.ioc.di.web;

import com.alpha.ioc.di.IocContainer;
import com.alpha.ioc.di.util.PackageScanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class IocInitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        try {
            URL root = context.getResource("/");
            File file = new File(root.toURI());

            IocContainer iocContainer = new IocContainer();
            iocContainer.initialize(file);
            context.setAttribute("iocContainer", iocContainer);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
