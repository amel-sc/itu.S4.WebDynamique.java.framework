package mg.itu.framework.util;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class StringUtil {
    // function to capitalize first string
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

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
        else if (toCast == LocalDate.class) {
            return LocalDate.parse(value);
        }
        else if (toCast == LocalTime.class) {
            return LocalTime.parse(value);
        }
        else {
            return null;
        }
    }
}
