package mg.itu.framework.util;

import mg.itu.framework.model.MapSession;

import java.util.Enumeration;
import java.util.Map;

import jakarta.servlet.http.*;

public class SessionUtil {
    // function to get Value from HttpSession
    public void initMapSession(HttpSession httpsession, MapSession mapsession) {
        Enumeration<String> httpsession_keys = httpsession.getAttributeNames();

        while (httpsession_keys.hasMoreElements()) {
            String key = httpsession_keys.nextElement();

            mapsession.add(key, httpsession.getAttribute(key));
        }
    }

    // function to rectify values in HttpSession (Après invoke)
    public void rectifyHttpSession(HttpSession httpsession, MapSession mapsession) {
        for (Map.Entry<String, Integer> entry : mapssession..entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
