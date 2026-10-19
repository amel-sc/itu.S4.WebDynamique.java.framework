package mg.itu.framework.util;

import mg.itu.framework.model.MapSession;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.*;

public class SessionUtil {
    // function to get Value from HttpSession
    public static void initMapSession(HttpSession httpsession, MapSession mapsession) {
        Enumeration<String> httpsession_keys = httpsession.getAttributeNames();

        while (httpsession_keys.hasMoreElements()) {
            String key = httpsession_keys.nextElement();

            mapsession.add(key, httpsession.getAttribute(key));
        }
    }

    // function to rectify values in HttpSession (Après invoke)
    public static void rectifyHttpSession(HttpSession httpsession, MapSession mapsession) {
        Set<String> keys = mapsession.getKeys();

        for (String key : keys) {
            httpsession.setAttribute(key, mapsession.get(key));
        }
    }

    // function to verify if list object has session
    public static MapSession getMapSessionFromObject(Object[] parameters) {
        MapSession mapSession = null;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof MapSession) {
                mapSession = (MapSession) parameters[i];
                break;
            }
        }

        return mapSession;
    } 
}
