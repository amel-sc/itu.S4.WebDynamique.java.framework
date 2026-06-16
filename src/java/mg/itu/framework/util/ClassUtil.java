package mg.itu.framework.util;

import java.net.URL;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import mg.itu.framework.annotation.*;

public class ClassUtil {
    // function to get class by package and annotation
    public List<String> getClassByPackageAnnotation(String packageName, Annotation annotation) throws Exception {
        List<String> classes = new ArrayList<String>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        URL resource = classLoader.getResource(path);

        if (resource == null) {
            throw new Exception("Package not found : "+packageName);
        }

        File directory = new File(resource.toURI());
        File[] files = directory.listFiles()

        if (files == null) {
            return classes;
        }

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String classSimpleName = file.getName().replace(".class", "");
                String className = packageName + "." + classSimpleName;
                
                Class<?> temp_class = Class.forName(className);

                if (temp_class.getAnnotation(Controller.class) != null) {
                    classes.add(className);
                }
            }
        }

        return classes;
    }
}
