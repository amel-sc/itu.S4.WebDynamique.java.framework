package mg.itu.framework.util;

import java.util.Map;

public class ControllerUtil {
    // function add new controller in controllers
    public static void add(Map<Class<?>, Object> controllers, Class<?> controllerClass) throws Exception {
        // create new instance of controller
        Object controller = controllerClass.getDeclaredConstructor().newInstance();

        controllers.put(controllerClass, controller);
    }
}
