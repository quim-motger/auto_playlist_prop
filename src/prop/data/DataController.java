package prop.data;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class DataController contains static methods to save and load data using files.
 * @author Carles Garcia Cabot
 */
public class DataController {
    private File file;
    private BufferedReader br;

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
     * @return String that contains the bytes of the file
     * @throws IOException
     */
    public static String load(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    /**
     * Opens a file and stores it inside 
     * @param path path to file
     */
    public void open(String path) {
        file = new File(path);        
    }

    /**
     * prepares the opened file to read
     * @throws FileNotFoundException
     */
    public void openToRead() throws FileNotFoundException {
        InputStream fis = new FileInputStream(file);
        br = new BufferedReader(new InputStreamReader(fis));
    }

    /**
     * Appends a line at the end of the opened file 
     * @param data line to be appended
     * @throws IOException File doesn't exist
     */
    public void append(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    /**
     * Writes a string to the file previously opened (overwrites content * 
     * @param data String to be written
     * @throws IOException File doesn't exist
     */
    public void write(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    /**
     * Reads a line from the opened file (it iterates inside the file)
     * @return readen line
     * @throws IOException File doesn't exist
     */
    public String readLine() throws IOException {
        return br.readLine();
    }

    /**
     * Deletes content from the file previously opened
     * @throws FileNotFoundException File doesn't exist
     */
    public void deleteContent() throws FileNotFoundException {
        FileOutputStream writer = new FileOutputStream(file);

    }
}
