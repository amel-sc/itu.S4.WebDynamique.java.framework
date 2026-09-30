package mg.itu.framework.util;

import java.time.LocalDateTime;

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
        else if (toCast == double.class || toCast == Double.class) {
            return Double.parseDouble(value);
        }
        else if (toCast == LocalDateTime.class) {
            return LocalDateTime.parse(value);
        }
        else {
            return null;
        }
    }
}
