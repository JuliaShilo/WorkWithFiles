package org.itstep.Service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


class FileManagerServiceTest {
    private static String jpgfromPath;
    private static String jpgtoPath;
    private static String txtFilefromPath;
    private static String txtFiletoPath;

    @BeforeAll
    static void setPreData() {
        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");

        jpgfromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "Cat.jpg";
        jpgtoPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "Cat_test_copy.jpg";

        txtFilefromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text.txt";
        txtFiletoPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text_test.txt";
    }

    @Test
    void testGetFileAsByteArray() {
        byte[] bytes = FileManagerService.getFileAsByteArray(jpgfromPath);
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void testWriteByteArrayToFile() {
        byte[] bytes = FileManagerService.getFileAsByteArray(jpgfromPath);

        FileManagerService.writeByteArrayToFile(bytes, jpgtoPath);
        byte[] testBytes = FileManagerService.getFileAsByteArray(jpgtoPath);

        assertNotNull(testBytes);
        assertTrue(testBytes.length > 0);
    }


    @Test
    void testGetTextFromFile() {
        String text = FileManagerService.getTextFromFile(txtFilefromPath);
        assertNotNull(text);
        assertTrue(text.contains("someLine1\n"));
        assertTrue(text.contains("someLine2\n"));
        assertTrue(text.contains("Line3\n"));
    }

    @Test
    void testWriteTexttoFileWithoutAppend() {
        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerService.writeTexttoFile(txtFiletoPath, text, false);

        String testText = FileManagerService.getTextFromFile(txtFiletoPath);

        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerService.writeTexttoFile(txtFiletoPath, text, false);

        testText = FileManagerService.getTextFromFile(txtFiletoPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n");
    }

    @Test
    void testWriteSymbolsToFile() {
        String text = "!@#$$%^&**(()";

        FileManagerService.writeTexttoFile(txtFiletoPath, text, false);

        String testText = FileManagerService.getTextFromFile(txtFiletoPath);

        assertNotNull(testText);
        assertEquals(testText,text+"\n");
    }

    @Test
    void testWriteTexttoFileWithAppend() {
        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerService.writeTexttoFile(txtFiletoPath, text, false);

        String testText = FileManagerService.getTextFromFile(txtFiletoPath);

        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerService.writeTexttoFile(txtFiletoPath, text, true);

        testText = FileManagerService.getTextFromFile(txtFiletoPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n" + text + "\n");
    }


    @AfterAll
    static void removeTestFiles() {
        File jpgfile = new File(jpgtoPath);
        File txtfile = new File(txtFiletoPath);

        if (jpgfile.isFile()) {
            try {
                Files.delete(jpgfile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (txtfile.isFile()) {
            try {
                Files.delete(txtfile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
