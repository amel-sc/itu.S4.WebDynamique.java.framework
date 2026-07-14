package mg.itu.framework.model;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private String view;
    private Map<String, Object> model = new HashMap<String, Object>();

    // getters / setters
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    // function to add attribute in model
    public void setAttribute(String name, Object object) {
        this.model.put(name, object);
    }
}