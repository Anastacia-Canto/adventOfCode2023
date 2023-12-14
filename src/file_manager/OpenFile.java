package file_manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OpenFile {

    public static List<String> open(String path) {
        List<String> fileInfo = new ArrayList<>();
        try {
            fileInfo = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
        return fileInfo;
    }
}
