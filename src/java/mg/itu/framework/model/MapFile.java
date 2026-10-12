package mg.itu.framework.model;

public class MapFile {
    private String filename;
    private String extension;
    private Byte[] content;

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public Byte[] getContent() {
        return content;
    }
    public void setContent(Byte[] content) {
        this.content = content;
    }
}
