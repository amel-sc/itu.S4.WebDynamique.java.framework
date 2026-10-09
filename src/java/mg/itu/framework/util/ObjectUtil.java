package mg.itu.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ObjectUtil {
    // function to verify if a string exist in parameter
    public static boolean parameterContains(Map<String, String[]> parameters, String toVerify) {
        boolean contains = false;

        for (String key : parameters.keySet()) {
            if (key.contains(toVerify)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    // function to set value in all object's attribute
    public static void setAttributesObject(HttpServletRequest req, Object obj, String parameterName) throws Exception {
        // get object fields
        Field[] fields = obj.getClass().getDeclaredFields();
        // get list of url parameters as map
        Map<String, String[]> paramAsMap = req.getParameterMap();
        // set value in attributes of object
        String value = "";
        for (int i = 0; i < fields.length; i++) {
            String param_str = parameterName+"."+fields[i].getName();

            if (parameterContains(paramAsMap, param_str+".")) {
                System.out.println("'"+param_str+".' : Existe");
            } 

            value = req.getParameter(parameterName+"."+fields[i].getName());
            setObject(obj, fields[i], value);
            System.out.println("Field ("+fields[i].getName()+") : "+getObject(obj, fields[i]));
        }
    }

    // function to get value in object's attribute
    public static Object getObject(Object obj, Field field) throws Exception {
        // create method name
        String methodName = "get" + StringUtil.capitalize(field.getName());
        // get set method
        Method setMethod = obj.getClass().getMethod(methodName);
        // execute set method
        Object value = setMethod.invoke(obj);

        return value;
    }

    // function to set value in one object's attribute
    public static void setObject(Object obj, Field field, String value) throws Exception {
        // create method name
        String methodName = "set" + StringUtil.capitalize(field.getName());
        // get set method
        Method setMethod = obj.getClass().getMethod(methodName, field.getType());
        // execute set method
        setMethod.invoke(obj, StringUtil.convert(value, field.getType()));
    }
}
