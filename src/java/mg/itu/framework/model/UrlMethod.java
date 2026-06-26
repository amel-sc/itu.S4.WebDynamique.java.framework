package mg.itu.framework.model;

import java.util.Objects;

public class UrlMethod {
    private String url;
    private String method;

    // getter / setter
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    // constructor
    public UrlMethod(String url, String method) {
        this.url = url;
        this.method = method;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        UrlMethod other = (UrlMethod) obj;
        if (this.url.trim().equalsIgnoreCase(other.url.trim()) && this.method.trim().equalsIgnoreCase(other.method.trim())) {
            return true;
        } 

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.url, this.method);
    }
}
