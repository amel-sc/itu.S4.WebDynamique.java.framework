package mg.itu.framework.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapSession {
    private Map<String, Object> maps = new HashMap<String, Object>();

    // function to add in map
    public void add(String key, Object value) {
        maps.put(key, value);
    } 

    // function to get in map
    public Object get(String key) {
        return maps.get(key);
    }

    // functoin to remove in map
    public void remove(String key) {
        maps.remove(key);
    }

    // function to get keys of map
    public Set<String> getKeys() {
        return maps.keySet();
    }
}
