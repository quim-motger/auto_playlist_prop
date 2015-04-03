package prop.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Class DataController contains static methods to save and load data using files.
 * @author Carles Garcia Cabot
 */
public class DataController {
    /**
     * Saves the contents of the string parameter to a file in the specified path. If the file already exists, it will
     * be overridden. Else, it will be created.
     * @param data String containing the data to save
     * @param path String path of the file to write.
     * @throws IOException
     */
    public static void save(String data, String path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.defaultCharset())) {
            writer.write(data, 0, data.length());
        }
    }

    /**
     * Loads all the content from a file to a string
     * @param path String path of the file to load
     * @return String that containing the bytes of the file
     * @throws IOException
     */
    public static String load(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    /**
     * Loads all lines from a file in the specified path to an ArrayList of strings. <br>
     * Each string contains a line, not including any line-termination characters.
     * @param path String path of the file to load
     * @return ArrayList of strings with the line of the file in order.
     * @throws IOException
     */
    public static ArrayList<String> loadAllLines(String path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.defaultCharset())) {
            String line = null;
            ArrayList<String> file = new ArrayList<String>();
            while ((line = reader.readLine()) != null) file.add(line);
            return file;
        }
    }
}
