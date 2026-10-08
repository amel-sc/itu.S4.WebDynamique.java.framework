package mg.itu.framework.util;

import java.lang.reflect.Parameter;

import mg.itu.framework.annotation.Json;
import mg.itu.framework.annotation.ModelAttribute;

public class ParamsUtil {
    // fonction to verify if parameters have annotation
    public boolean hasModelAttributeAnnotation(Parameter param) {
        boolean hasAnnotation = false;

        ModelAttribute annotation = param.getAnnotation(ModelAttribute.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        return hasAnnotation;
    }
}
