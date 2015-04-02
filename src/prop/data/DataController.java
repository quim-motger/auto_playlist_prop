package prop.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Carles Garcia Cabot
 */
public class DataController {
    public static void save(String data, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.defaultCharset())) {
            writer.write(data, 0, data.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
    public static String load(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }
}
