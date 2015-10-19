package ua.nure.lisyak.SummaryTask4.util.fileProcessing;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@link FileService} interface.
 */
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class);
    private final String basePath;

    /**
     * @param folderPath root folder for files
     */
    public FileServiceImpl(String folderPath) {
        this.basePath = folderPath + File.separator;
        File fileSaveDir = new File(folderPath);
        if (!fileSaveDir.exists() && !fileSaveDir.mkdirs()) {
            LOGGER.warn("Folder wasn't create");
            throw new IllegalStateException("Unexpected result when creating folder");
        }
    }

    @Override
    public String saveFile(Integer name, String subDirectory, Part file) {
        return saveFile("" + name, subDirectory, file);
    }

    @Override
    public String saveFile(String name, String subDirectory, Part file) {
        String extension = getExtension(file);
        String fileName = generateFileName(name, extension);
        String path = basePath + subDirectory + fileName;
        writeFileOnDisk(file, path);
        LOGGER.debug("File saved to  " + path);
        return fileName;
    }

    @Override
    public File getFile(String path) {
        return new File(basePath + path);
    }

    @Override
    public void removeFile(String name, String subDirectory) {
        if (name == null || name.isEmpty()) {
            return;
        }
        String path = basePath + subDirectory + name;
        removeFile(path);
    }

    @Override
    public void removeFiles(List<String> names, String subDirectory) {
        for (String name : names) {
            removeFile(name, subDirectory);
        }
    }

    @Override
    public String getFormat(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return null;
        }
        try {
            return fileName.substring(index + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Cannot get file extension", e);
        }
    }

    private String getExtension(Part file) {
        String fileName = getFileName(file);
        return getFormat(fileName);
    }

    private String getFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");
        if (header == null) {
            return null;
        }
        for (String headerPart : header.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    private void writeFileOnDisk(Part file, String filePath) {
        try {
            file.write(filePath);
        } catch (IOException e) {
            LOGGER.error("File cannot be written on disk", e);
            throw new IllegalStateException("File cannot be save", e);
        }
    }

    private String generateFileName(String name, String extension) {
        return name + "." + extension;
    }

    private void removeFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            LOGGER.debug("File deleted from " + path);
        } else {
            LOGGER.error("File deletion failed. Path: " + path);
        }
    }

}
