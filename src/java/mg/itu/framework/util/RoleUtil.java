package mg.itu.framework.util;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {
    // function to convert string to List<String>
    public static List<String> splitRoles(String str) {
        List<String> splited = new ArrayList<String>();

        String[] roles = str.split(",");
        for (int i = 0; i < roles.length; i++) {
            splited.add(roles[i].trim());
        }

        return splited;
    }
}
