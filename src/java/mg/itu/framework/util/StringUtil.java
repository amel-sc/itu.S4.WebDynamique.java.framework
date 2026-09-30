package mg.itu.framework.util;

public class StringUtil {
    // function to convert string to other type
    public static Object convert(String value, Class<?> toCast) {
        if (toCast == String.class) {
            return value;
        }
        else if (toCast == int.class || toCast == Integer.class) {
            return Integer.parseInt(value);
        }
        else {
            return value;
        }
    }
}
