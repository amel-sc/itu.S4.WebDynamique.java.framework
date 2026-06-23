package mg.itu.framework.util;

import java.net.URL;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mg.itu.framework.annotation.UrlMapping;

public class ClassUtil {
    // function to get class by package and annotation
    public static List<Class<?>> getClassByPackageAnnotation(String packageName, Class<? extends Annotation> annotation) throws Exception {
        List<Class<?>> classes = new ArrayList<Class<?>>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        URL resource = classLoader.getResource(path);

        if (resource == null) {
            throw new Exception("Package not found : "+packageName);
        }

        File directory = new File(resource.toURI());
        File[] files = directory.listFiles();

        if (files == null) {
            return classes;
        }

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String classSimpleName = file.getName().replace(".class", "");
                String className = packageName + "." + classSimpleName;
                
                Class<?> temp_class = Class.forName(className);

                if (temp_class.getAnnotation(annotation) != null) {
                    classes.add(temp_class);
                }
            }
        }

        return classes;
    }

    // function to findAllUrlMapping
    public static Map<String, Method> findAllUrlMapping(List<Class<?>> controllers, Class<? extends Annotation> annotation) {
        Map<String, Method> urlMapped = new HashMap<String, Method>();

        for (int i = 0; i < controllers.size(); i++) {
            Method[] methods = controllers.get(i).getDeclaredMethods();

            for (int j = 0; j < methods.length; j++) {
                String temp_url = ClassUtil.findUrlByMethod(methods[j], annotation);
                urlMapped.put(temp_url, methods[j]);
            }
        }

        return urlMapped;
    }

    // function to get annotation in method
    public static String findUrlByMethod(Method method, Class<? extends Annotation> annotation) {
        String url = "";

        // get annotation of Method
        Annotation annot = method.getAnnotation(annotation);
        if (annot != null) {
            if (annot instanceof UrlMapping) {
                url = ((UrlMapping) annot).url();
            }
        }

        return url;
    }
}
