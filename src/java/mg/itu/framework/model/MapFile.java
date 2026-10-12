package mg.itu.framework.model;

public class MapFile {
    private String fileName;
    private String extension;
    private Byte[] content;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
