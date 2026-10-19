package mg.itu.framework.util;

import mg.itu.framework.model.MapSession;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SessionUtil {
    // function to get Value from HttpSession
    public static void initMapSession(HttpSession httpsession, ServletContext context) {
        Enumeration<String> httpsession_keys = httpsession.getAttributeNames();

        createMapSessionContext(context);

        while (httpsession_keys.hasMoreElements()) {
            String key = httpsession_keys.nextElement();

           ((MapSession) context.getAttribute("mapsession")).add(key, httpsession.getAttribute(key));
        }
    }

    // function to rectify values in HttpSession (Après invoke)
    public static void rectifyHttpSession(HttpSession httpsession, ServletContext context) {
        Set<String> keys = ((MapSession) context.getAttribute("mapsession")).getKeys();

        createMapSessionContext(context);

        for (String key : keys) {
            httpsession.setAttribute(key, ((MapSession) context.getAttribute("mapsession")).get(key));
        }
    }

    // function to verify if list object has session
    public static boolean verifyMapSessionObject(Object[] parameters) {
        boolean hasSession = false;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof MapSession) {
                hasSession = true;
                break;
            }
        }

        return hasSession;
    } 

    // function to verify if MapSession is already in context
    public static boolean verifyMapSessionContext(ServletContext context) {
        boolean sessionExist = false;

        if (context.getAttribute("mapsession") != null) {
            sessionExist = true;
        }

        return sessionExist;
    }

    // function to create session if not exist in context
    public static void createMapSessionContext(ServletContext context) {
        if (!verifyMapSessionContext(context)) {
            context.setAttribute("mapsession", new MapSession());
        }
    }
}
