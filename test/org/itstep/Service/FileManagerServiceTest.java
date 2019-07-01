package org.itstep.Service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


class FileManagerServiceTest {
    private static String fromPath;
    private static String toPath;

    @BeforeAll
    static void setPreData() {
        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        fromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "Cat.jpg";
        toPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "Cat_test_copy.jpg";
    }

    @Test
    void testGetFileAsByteArray() {
        byte[] bytes = FileManagerService.getFileAsByteArray(fromPath);
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void writeByteArrayToFile() {
        byte[] bytes = FileManagerService.getFileAsByteArray(fromPath);

        FileManagerService.writeByteArrayToFile(bytes, toPath);
        byte [] testBytes = FileManagerService.getFileAsByteArray(toPath);

        assertNotNull (testBytes);
        assertTrue (testBytes.length > 0);
    }


    @AfterAll
    static void removeTestFiles() {
        File file = new File(toPath);
        if (file.isFile()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    @BeforeAll
    static void setPreData2() {
        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");
        fromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text.txt";
        toPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text_test_copy.txt";
    }

    @Test
    void getTextFromFile () {
        String text = FileManagerService.getTextFromFile(fromPath);
        assertNotNull(text);
    }

    @Test
    void writeTexttoFile () {
        String text = FileManagerService.writeTexttoFile(fromPath);

        FileManagerService.writeTexttoFile(text, toPath);
        String testText = FileManagerService.writeTexttoFile(toPath);

        assertNotNull (testText);

    }

    @AfterAll
    static void removeTestFiles2() {
        File file = new File(toPath);
        if (file.isFile()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}