package mg.itu.framework.util;

public class StringUtil {
    // function to convert string to other type
    public static Object convert(String value, Class<?> toCast) throws Exception {
        if (toCast == String.class) {
            return value;
        }
        else if (toCast == int.class || toCast == Integer.class) {
            return Integer.parseInt(value);
        }
        else if (toCast == long.class || toCast == Long.class) {
            return Long.parseLong(value);
        }
        else {
            return value;
        }
    }
}
