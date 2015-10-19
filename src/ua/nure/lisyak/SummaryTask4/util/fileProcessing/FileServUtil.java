package ua.nure.lisyak.SummaryTask4.util.fileProcessing;


import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Service for writing files to {@link HttpServletResponse} output stream
 */
public final class FileServUtil {

    private static final Logger LOGGER = Logger.getLogger(FileServUtil.class);

    /**
     * Writes file to HttpServletResponse output stream
     * @param file file to be written
     * @param response response with the output stream
     */
    public static void write(File file, HttpServletResponse response) {
        try (FileInputStream stream = new FileInputStream(file)) {
            writeStream(stream, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("Unexpected exception while writing file", e);
        }
    }

    private static void writeStream(InputStream is, OutputStream os) throws IOException {
        final byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > -1) {
            os.write(buffer, 0, length);
        }
        os.flush();
    }

}
