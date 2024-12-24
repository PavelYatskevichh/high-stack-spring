package com.yatskevich.hs.spring.content_creation;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.util.ResourceUtils;

/**
 * File utils for tests.
 */
public final class FileUtils {

    /**
     * Read json inside file as string.
     *
     * @param fileName file location
     * @return file content as string
     * @throws IOException in case of file exceptions
     */
    public static String getJsonAsString(String fileName) throws IOException {
        return Files.readString(ResourceUtils.getFile(fileName).toPath());
    }
}
