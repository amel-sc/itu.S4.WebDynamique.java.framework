package mg.itu.framework.model;

import java.util.HashMap;
import java.util.Map;

public class MapSession {
    Map<String, Object> maps = new HashMap<String, Object>();

    // function to add in map
    public void add(String key, Object value) {
        maps.put(key, value);
    } 
}
