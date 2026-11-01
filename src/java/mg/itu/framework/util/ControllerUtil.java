package mg.itu.framework.util;

import java.util.Map;

public class ControllerUtil {
    // function to print list in controllers
    public static void showAll(Map<Class<?>, Object> controllers) {
        for (Map.Entry<Class<?>, Object> entry : controllers.entrySet()) {
            System.out.println(entry.getKey().getName()+" : "+entry.getValue());
        }
    }

    // function to get controller by class
    public static Object get(Map<Class<?>, Object> controllers, Class<?> controllerClass) throws Exception {
        if (controllers.containsKey(controllerClass)) {
            return controllers.get(controllerClass);
        }
        else {
            add(controllers, controllerClass);

            return controllers.get(controllerClass);
        }
    }

    // function add new controller in controllers
    public static void add(Map<Class<?>, Object> controllers, Class<?> controllerClass) throws Exception {
        // create new instance of controller
        Object controller = controllerClass.getDeclaredConstructor().newInstance();

        controllers.put(controllerClass, controller);
    }
}
