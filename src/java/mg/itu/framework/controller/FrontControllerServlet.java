package mg.itu.framework.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.ArrayList;
import java.util.List;

import mg.itu.framework.util.*;
import mg.itu.framework.annotation.Controller;

public class FrontControllerServlet extends HttpServlet {
    private List<String> listController;

    public void init() throws ServletException {
        listController = new ArrayList<String>();

        String packageName = this.getInitParameter("packageName"); 

        try {
            List<Class<?>> listClassController = ClassUtil.getClassByPackageAnnotation(packageName, Controller.class);
            for (int i = 0; i < listClassController.size(); i++) {
                listController.add(listClassController.get(i).getSimpleName());
            }
            
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

        // get last in URI
        String lastInUri = "";
        if (splited.length > 2) {
            lastInUri = splited[splited.length - 1];
        }

        // print uri
        out.println("Resultat : "+lastInUri);

        // show list of controller in package controller
        out.println("Controller list : ");
        for (int i = 0; i < listController.size(); i++) {
            out.println((i+1)+" - "+listController.get(i));
        }
    }
}