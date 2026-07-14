package mg.itu.framework.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Method;

import mg.itu.framework.util.*;
import mg.itu.framework.annotation.Controller;
import mg.itu.framework.annotation.UrlMapping;
import mg.itu.framework.model.UrlMethod;
import mg.itu.framework.model.ModelAndView;

public class FrontControllerServlet extends HttpServlet {
    private Map<UrlMethod, Method> listUrl;
    private String prefixe;
    private String suffixe;
    private WebApplicationContext applicationContext;

    public void init() throws ServletException {
        ServletContext servletContext = this.getServletContext();

        try {
            this.listUrl = (Map<UrlMethod, Method>) servletContext.getAttribute("listUrl");
            this.prefixe = (String) servletContext.getAttribute("prefixe");
            this.suffixe = (String) servletContext.getAttribute("suffixe");
            this.applicationContext = (WebApplicationContext) servletContext.getAttribute("applicationContext");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        
        // processRequest
        this.processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        
        // processRequest
        this.processRequest(req, res);
    }

    // function to get uri 
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        // get URI
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        // get after url
        String afterUrl = uri.substring(contextPath.length());

        // get method
        String method = req.getMethod();
    
        // create new UrlMethod object
        UrlMethod wantedUrlMethod = new UrlMethod(afterUrl, method);

        // url wanted
        out.println("Url : "+wantedUrlMethod.getUrl()+", "+"Method : "+wantedUrlMethod.getMethod());

        out.println();
        
        out.println("Url with method : ");     
        if (listUrl.containsKey(wantedUrlMethod)) {
            out.println(wantedUrlMethod.getUrl()+", "+wantedUrlMethod.getMethod()+" - "+listUrl.get(wantedUrlMethod).getDeclaringClass().getName()+" - "+listUrl.get(wantedUrlMethod).getName());

            this.executeUrlMethod(req, res, wantedUrlMethod);
        }
        else {
            for (UrlMethod i : listUrl.keySet()) {
                out.println(i.getUrl()+", "+i.getMethod()+" - "+listUrl.get(i).getDeclaringClass().getName()+" - "+listUrl.get(i).getName());
            }   
        }
    }

    // function to execute wantedUrlMethod
    public void executeUrlMethod(HttpServletRequest req, HttpServletResponse res, UrlMethod wantedUrlMethod) {
        try {
            // get method
            Method mappedMethod = listUrl.get(wantedUrlMethod);
            // get class controller
            Class<?> controllerClass = mappedMethod.getDeclaringClass();
            // create new instance of controller
            Object controller = controllerClass.getDeclaredConstructor().newInstance();

            if (listUrl.get(wantedUrlMethod).getReturnType() == ModelAndView.class) {
                // get list of parameters types for method
                Class<?>[] parameters = mappedMethod.getParameterTypes();
                // invoke the method
                ModelAndView modelAndView = null;
                if (parameters.length == 0) {
                    modelAndView = (ModelAndView) mappedMethod.invoke(controller);
                }
                else if (parameters.length == 1 && parameters[0].isInstance(applicationContext)) {
                    modelAndView = (ModelAndView) mappedMethod.invoke(controller, applicationContext);
                }
                else {
                    throw new Exception("La méthode vulue n'a pas de paramètre 'applicationContext'");
                }
                // url for wanted view
                String view_path = this.prefixe + modelAndView.getView() + this.suffixe;
                
                // add model in request
                for (String key : modelAndView.getModel().keySet()) {
                    req.setAttribute(key, modelAndView.getModel().get(key));
                }

                // forward dispatcher
                RequestDispatcher dispat = req.getRequestDispatcher(view_path);
                dispat.forward(req, res);
            }
            else {
                throw new Exception("La méthode voulue ne retourne pas un Objet de type ModelAndView");
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}