package mg.itu.framework.util;

import java.lang.reflect.Method;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AuthUtil {
    // function to verify auth
    public static void verifyAuth(HttpServletRequest req, Method mappedMethod, String sessionAuth) throws Exception {
        if (ClassUtil.HasAuthAnnotation(mappedMethod)) {
            if (SessionUtil.getMapSessionContext(req) == null || SessionUtil.getMapSessionContext(req).get(sessionAuth) == null) {
                throw new Exception("Page inaccessible car vous n'êtes pas authentifier");
            }
        }
    }
}
