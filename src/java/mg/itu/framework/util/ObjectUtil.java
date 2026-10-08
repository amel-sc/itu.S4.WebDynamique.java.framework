package mg.itu.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectUtil {
    // function to set value in an object
    public void setObject(Object obj, Field field, String value) throws Exception {
        // create method name
        String methodName = "set" + StringUtil.capitalize(field.getName());
        // get set method
        Method setMethod = obj.getClass().getMethod(methodName, field.getType());
        // execute set method
        setMethod.invoke(obj, StringUtil.convert(value, field.getType()));
    }
}
