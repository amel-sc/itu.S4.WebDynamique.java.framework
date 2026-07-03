package mg.itu.framework.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

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

    public void init() throws ServletException {
        try {
            this.listUrl = (Map<UrlMethod, Method>) this.getServletContext().getAttribute("listUrl");
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

        // get method
        String method = req.getMethod();

        // separate URI by /
        String[] splited = uri.split("/");

        // servlet name
        String servletName = splited[1];
        // get after url
        String afterUrl = uri.substring(uri.indexOf(servletName) + servletName.length());
    
        // create new UrlMethod object
        UrlMethod wantedUrlMethod = new UrlMethod(afterUrl, method);

        // url wanted
        out.println("Url : "+wantedUrlMethod.getUrl()+", "+"Method : "+wantedUrlMethod.getMethod());

        out.println();
        
        out.println("Url with method : ");     
        if (listUrl.containsKey(wantedUrlMethod)) {
            out.println(wantedUrlMethod.getUrl()+", "+wantedUrlMethod.getMethod()+" - "+listUrl.get(wantedUrlMethod).getDeclaringClass().getName()+" - "+listUrl.get(wantedUrlMethod).getName());

            // get prefixe and suffixe
            String prefixe = this.getInitParameter("prefixe");
            String suffixe = this.getInitParameter("suffixe");

            try {
                // create new instance of controller
                Object controller = listUrl.get(wantedUrlMethod).getDeclaringClass().getDeclaredConstructor().newInstance();
                // invoke the method
                ModelAndView modelAndView = (ModelAndView) listUrl.get(wantedUrlMethod).invoke(controller);
                // url for wanted view
                String view_path = prefixe + modelAndView.getView() + suffixe;

                // add model in request
                for (String key : modelAndView.getModel().keySet()) {
                    req.setAttribute(key, modelAndView.getModel().get(key));
                }

                // forward dispatcher
                RequestDispatcher dispat = req.getRequestDispatcher(view_path);
                dispat.forward(req, res);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            for (UrlMethod i : listUrl.keySet()) {
                out.println(i.getUrl()+", "+i.getMethod()+" - "+listUrl.get(i).getDeclaringClass().getName()+" - "+listUrl.get(i).getName());
            }   
        }
    }
}