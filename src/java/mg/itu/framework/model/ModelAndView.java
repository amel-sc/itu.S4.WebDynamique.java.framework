package mg.itu.framework.model;

import java.util.Map;

public class ModelAndView {
    private String view;
    private Map<String, Object> model;

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
}