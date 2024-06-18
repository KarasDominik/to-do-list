package com.karasdominik;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class FileUtils {

    public static String fetchJsonFrom(String filePath) throws IOException {
        var file = new FileInputStream(filePath);
        return IOUtils.toString(file, UTF_8);
    }
}
