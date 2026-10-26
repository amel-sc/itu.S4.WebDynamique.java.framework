package mg.itu.framework.util;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {
    // function to verify if String exist in list of string
    public static boolean roleExist(List<String> roles, String role) {
        boolean exist = false;

        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).equalsIgnoreCase(role)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

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
