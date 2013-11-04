package org.ict4h.forms.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static File createTempFile(String xml) throws IOException {
        final File tempFile = File.createTempFile("enketo", ".xml");
        org.apache.commons.io.FileUtils.writeStringToFile(tempFile, xml);
        return tempFile;
    }
}
