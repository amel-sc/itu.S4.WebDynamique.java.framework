package mg.itu.framework.util;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Method;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import mg.itu.framework.annotation.Auth;
import mg.itu.framework.annotation.Role;

public class RoleUtil {
    // function to verify if method has annotation
    public static void verifyRole(HttpServletRequest req, Method mappedMethod, String sessionAuth, String sessionRole) throws Exception {
        // verify auth first
        AuthUtil.verifyAuth(req, mappedMethod, sessionAuth);

        // verify role
        Role annotation = mappedMethod.getAnnotation(Role.class);
        if (annotation != null) {
            // verify if sessionRole exist session
            if (SessionUtil.getMapSessionContext(req) == null || SessionUtil.getMapSessionContext(req).get(sessionRole) == null) {
                throw new Exception("Le rôle doit être spécifier avant son utilisation");
            }
            // verify if rôle is correct in mappedMethod
            String roles = annotation.roles();
            if (roles != null || roles.trim() != "") {
                if (!roleExist(splitRoles(roles), SessionUtil.getMapSessionContext(req).get(sessionRole))) {
                    throw new Exception("Vous avez le rôle pour accéder à cette page.");
                }
            }
        }
    }

    // function to verify if String exist in list of string
    public static boolean roleExist(List<String> roles, String role) {
        boolean exist = false;

        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).equalsIgnoreCase(role)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    // function to convert string to List<String>
    public static List<String> splitRoles(String str) {
        List<String> splited = new ArrayList<String>();

        String[] roles = str.split(",");
        for (int i = 0; i < roles.length; i++) {
            splited.add(roles[i].trim());
        }

        return splited;
    }
}
