package mg.itu.framework.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jakarta.servlet.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mg.itu.framework.model.UrlMethod;
import mg.itu.framework.util.*;
import mg.itu.framework.annotation.*;

@WebListener
public class AppStartUpListener implements ServletContextListener {
    private List<String> listController = new ArrayList<String>();
    private Map<UrlMethod, Method> listUrl = new HashMap<UrlMethod, Method>();
    private String prefixe;
    private String suffixe;
    // context for spring
    private WebApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String packageName = servletContext.getInitParameter("packageName");
        this.prefixe = servletContext.getInitParameter("prefixe");
        this.suffixe = servletContext.getInitParameter("suffixe");
        // get spring context
        this.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        // get list url
        try {
            List<Class<?>> listClassController = ClassUtil.getClassByPackageWithUrlMapping(packageName, Controller.class, listUrl);
            // save values in context
            servletContext.setAttribute("listUrl", this.listUrl);
            servletContext.setAttribute("prefixe", this.prefixe);
            servletContext.setAttribute("suffixe", this.suffixe);
            servletContext.setAttribute("applicationContexte", this.applicationContext);
        } catch (Exception e) {
            System.out.println(e.getMessage());
           throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application arrêtée !");
    }
}