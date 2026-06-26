package mg.itu.framework.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Method;

import mg.itu.framework.util.*;
import mg.itu.framework.annotation.Controller;
import mg.itu.framework.annotation.UrlMapping;
import mg.itu.framework.model.UrlMethod;

public class FrontControllerServlet extends HttpServlet {
    private List<String> listController;
    private Map<UrlMethod, Method> listUrl;

    public void init() throws ServletException {
        listController = new ArrayList<String>();

        String packageName = this.getInitParameter("packageName"); 

        try {
            List<Class<?>> listClassController = ClassUtil.getClassByPackageAnnotation(packageName, Controller.class);
            for (int i = 0; i < listClassController.size(); i++) {
                listController.add(listClassController.get(i).getSimpleName());
            }

            // find all url
            listUrl = ClassUtil.findAllUrlMapping(listClassController, UrlMapping.class);
            
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

        // separate URI by /
        String[] splited = uri.split("/");

        // servlet name
        String servletName = splited[1];
        // get after url
        String afterUrl = uri.substring(uri.indexOf(servletName) + servletName.length());
    
        // url wanted
        out.println("Url : "+afterUrl);

        out.println();
        
        out.println("Url with method : ");     
        if (listUrl.containsKey(afterUrl)) {
            out.println(afterUrl+" - "+listUrl.get(afterUrl).getDeclaringClass().getName()+" - "+listUrl.get(afterUrl).getName());
        }
        else {
            for (UrlMethod i : listUrl.keySet()) {
                out.println(i.getUrl()+", "+i.getMethod()+" - "+listUrl.get(i).getDeclaringClass().getName()+" - "+listUrl.get(i).getName());
            }   
        }
    }
}