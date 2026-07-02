package mg.itu.framework.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String packageName = sce.getServletContext().getInitParameter("packageName");
        // get list url
        try {
            List<Class<?>> listClassController = ClassUtil.getClassByPackageWithUrlMapping(packageName, Controller.class, listUrl);
            for (int i = 0; i < listClassController.size(); i++) {
                listController.add(listClassController.get(i).getSimpleName());
            }

            // save values in context
            sce.getServletContext().setAttribute("listController", this.listController);
            sce.getServletContext().setAttribute("listUrl", this.listUrl);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application arrêtée !");
    }
}