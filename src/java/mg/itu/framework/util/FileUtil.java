package mg.itu.framework.util;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import mg.itu.framework.model.MapFile;

public class FileUtil {
    // function to set data on MapFile
    public static void setMapFileData(HttpServletRequest req, MapFile mapfile, String parameterName) throws Exception {
        // get data to insert in mapfile
        Part file = req.getPart(parameterName);


        // get fileName and extension
        Map<String, String> fileInfo = getFileName(file.getSubmittedFileName());
        mapfile.setFileName(fileInfo.get("filename"));
        mapfile.setExtension(fileInfo.get("extension"));
        
        // get content as Byte[]
        mapfile.setContent(file.getInputStream().readAllBytes());
    } 

    // function to get extension of a fileName
    public static Map<String, String> getFileName(String fileName) {
        Map<String, String> result = new HashMap<String, String>();

        int dotIndex = -1;
        for (int i = (fileName.length() - 1); i >= 0; i--) {
          if (fileName.charAt(i) == '.') {
            dotIndex = i;
            break;
          }  
        } 

        // get fileName and extension
        result.put("filename", fileName.substring(0, dotIndex));
        result.put("extension", fileName.substring(dotIndex, fileName.length()));

        return result;
    }  
}
