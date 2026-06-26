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
import mg.itu.framework.model.UrlMethod;

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
    public static Map<UrlMethod, Method> findAllUrlMapping(List<Class<?>> controllers, Class<? extends Annotation> annotation) {
        Map<UrlMethod, Method> urlMapped = new HashMap<UrlMethod, Method>();

        for (int i = 0; i < controllers.size(); i++) {
            Method[] methods = controllers.get(i).getDeclaredMethods();

            for (int j = 0; j < methods.length; j++) {
                UrlMethod temp_urlMethod = ClassUtil.findUrlByMethod(methods[j], annotation);
                if (temp_urlMethod != null) {
                    urlMapped.put(temp_urlMethod, methods[j]);
                }
            }
        }

        return urlMapped;
    }

    // function to get annotation in method
    public static UrlMethod findUrlByMethod(Method method, Class<? extends Annotation> annotation) {
        UrlMethod url = null;

        // get annotation of Method
        Annotation annot = method.getAnnotation(annotation);
        if (annot != null) {
            if (annot instanceof UrlMapping) {
                url = new UrlMethod(((UrlMapping) annot).url(), ((UrlMapping) annot).method());
            }
        }

        return url;
    }
}
