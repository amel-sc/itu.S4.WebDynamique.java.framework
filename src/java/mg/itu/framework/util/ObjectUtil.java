package mg.itu.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ObjectUtil {
    // function to set value in all object's attribute
    public static void setAttributesObject(HttpServletRequest req, Object obj, String parameterName) throws Exception {
        // get object fields
        Field[] fields = obj.getClass().getFields();
        // set value in attributes of object
        String value = "";
        for (int i = 0; i < fields.length; i++) {
            value = req.getParameter(parameterName+"."+fields[i].getName());
            setObject(obj, fields[i], value);
            System.out.println("Field ("+fields[i].getName()+") : "+getObject(obj, fields[i], value));
        }
    }

    // function to get value in object's attribute
    public static Object getObject(Object obj, Field field, String value) throws Exception {
        // create method name
        String methodName = "get" + StringUtil.capitalize(field.getName());
        // get set method
        Method setMethod = obj.getClass().getMethod(methodName, field.getType());
        // execute set method
        Object returnedValue = setMethod.invoke(obj, StringUtil.convert(value, field.getType()));

        return returnedValue;
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
