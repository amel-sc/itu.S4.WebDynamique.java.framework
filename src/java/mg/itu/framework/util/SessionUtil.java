package mg.itu.framework.util;

import mg.itu.framework.model.MapSession;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SessionUtil {
    // function to get Value from HttpSession
    public static void initMapSession(HttpServletRequest req, MapSession mapsession) {
        Enumeration<String> httpsession_keys = req.getSession().getAttributeNames();

        if (mapsession == null) {
            mapsession = new MapSession();
        }

        while (httpsession_keys.hasMoreElements()) {
            String key = httpsession_keys.nextElement();

           mapsession.add(key, req.getSession().getAttribute(key));
        }

        createMapSessionContext(req, mapsession);
    }

    // function to rectify values in HttpSession (Après invoke)
    public static void rectifyHttpSession(HttpServletRequest req, MapSession mapsession) {
        Set<String> keys = mapsession.getKeys();

        for (String key : keys) {
            req.getSession().setAttribute(key, mapsession.get(key));
        }
    }

    // function to verify if list object has session
    public static boolean verifyMapSessionObject(HttpServletRequest req, Object[] parameters) {
        boolean hasSession = false;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof MapSession) {
                if (req.getServletContext().getAttribute("mapsession") == null) {
                    MapSession mapSession = (MapSession) parameters[i];
                    createMapSessionContext(req, mapSession);
                }
                hasSession = true;
                break;
            }
        }

        return hasSession;
    } 

    // function to set mapsession in ServletContext
    public static void createMapSessionContext(HttpServletRequest req, MapSession mapsession) {
        req.getServletContext().setAttribute("mapsession", mapsession);
    }

    // function to get mapsession in ServletContext
    public static MapSession getMapSessionContext(HttpServletRequest req) {
        return (MapSession) req.getServletContext().getAttribute("mapsession");
    }
}
