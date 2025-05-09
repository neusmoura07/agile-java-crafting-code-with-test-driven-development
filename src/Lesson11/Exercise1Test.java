package Lesson11;

import junit.framework.TestCase;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Exercise1Test extends TestCase {

    public void testWriteAndReadExerciseText() throws Exception {
        File arquivo = Files.createTempFile("exercise1", ".txt").toFile();
        try {
            String expected =
                    "1. Create a test to write the text of this exercise to the file system. The test should read the\n" +
                            "file back in and make assertions about the content. Ensure that you can run the test\n" +
                            "multiple times and have it pass. Finally, make sure that there are no leftover files when the\n" +
                            "test finishes, even if an exception is thrown.";

            Files.write(arquivo.toPath(), expected.getBytes(StandardCharsets.UTF_8));
            String actual = new String(Files.readAllBytes(arquivo.toPath()), StandardCharsets.UTF_8);

            assertEquals(expected, actual);
        } finally {
            arquivo.delete();
        }
    }



}
