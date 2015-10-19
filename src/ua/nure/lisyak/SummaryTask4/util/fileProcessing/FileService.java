package ua.nure.lisyak.SummaryTask4.util.fileProcessing;

import javax.servlet.http.Part;
import java.io.File;
import java.util.List;

/**
 *  File-managing util.
 */
public interface FileService {

    /**
     * Saves file with specified name to the specified directory.
     *
     * @param name         name to be saved with
     * @param subDir       relative path to file storage
     * @param file         {@link Part} that contains the file
     * @return generated name of saved file
     */
    String saveFile(Integer name, String subDir, Part file);

    /**
     * Saves file with specified name to the specified directory.
     *
     * @param name         name to be saved with
     * @param subDir       relative path to file storage
     * @param file         {@link Part} that contains the file
     * @return generated name of saved file
     */
    String saveFile(String name, String subDir, Part file);

    /**
     * Loads file from specified relative file.
     * @param path relative path to needed file 
     * @return file
     */
    File getFile(String path);

    /**
     * Removes file with specified name
     * @param name name of file to remove
     * @param subDir sub directory to look in
     */
    void removeFile(String name, String subDir);

    /**
     * Removes a {@link List} of files with the specified names
     * @param names names of files to remove
     * @param subDir sub directory to look in
     */
    void removeFiles(List<String> names, String subDir);

    /**
     * Gets the file format from name of file.
     * @param fileName name of file to be get from. 
     * @return format of specified file.
     * @throws ua.nure.SummaryTask4.exception.MediaException if extension cannot be obtained
     */
    String getFormat(String fileName);
}
