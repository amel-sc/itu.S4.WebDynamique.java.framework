package mg.itu.framework.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import mg.itu.framework.model.MapFile;

public class FileUtil {
    // function to verify if req has multipart/form-data
    public static boolean verifyEnctype(HttpServletRequest req) {
        return (req.getContentType() != null && (req.getContentType().toLowerCase().contains("multipart/form-data") || req.getContentType().toLowerCase().contains("multipart/mixed")));
    }

    // function to count mapfile having same name
    public static int getMapFileSize(HttpServletRequest req, String parameterName) throws Exception {
        Collection<Part> files = req.getParts();

        int counter = 0;
        for (Part part : files) {
            if (parameterName.equalsIgnoreCase(part.getName())) {
                counter = counter + 1;
            }
        }

        return counter;
    } 

    // function to set data on MapFile[]
    public static void setMapFileData(HttpServletRequest req, MapFile[] mapfile, String parameterName) throws Exception {
        // get data to insert in mapfile
        Collection<Part> files = req.getParts();

        int index = 0;
        for (Part part : files) {
            if (parameterName.equalsIgnoreCase(part.getName())) {
                Map<String, String> fileInfo = getFileName(part.getSubmittedFileName());
    
                mapfile[index] = new MapFile();
    
                mapfile[index].setFileName(fileInfo.get("filename"));
                mapfile[index].setExtension(fileInfo.get("extension"));
                // get content as Byte[]
                mapfile[index].setContent(part.getInputStream().readAllBytes());
    
                index = index + 1;
            }
        }
    } 

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

        Integer dotIndex = null;
        for (int i = (fileName.length() - 1); i >= 0; i--) {
          if (fileName.charAt(i) == '.') {
            dotIndex = i;
            break;
          }  
        } 

        // get fileName and extension
        if (dotIndex == null) {
            result.put("filename", fileName);
            result.put("extension", "");
        }
        else {
            result.put("filename", fileName.substring(0, dotIndex));
            result.put("extension", fileName.substring(dotIndex, fileName.length()));
        }

        return result;
    }  
}
