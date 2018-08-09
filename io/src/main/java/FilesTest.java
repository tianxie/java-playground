import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("e:\\57d8c571f7b3bbe082fe50d9\\a478464b-d152-46ae-bea3-2a27e6466a02");
        final Path dir = path.getParent();
        Files.createDirectories(dir);
        Files.createFile(path);
//        new Thread(() -> {
//            try {
//                Files.delete(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        while(true) {
//
//        }
    }
}
