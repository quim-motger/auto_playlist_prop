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
     * Opens a file in path
     * 
     * /
    public void open(String path) {
        file = new File(path);        
    }
    
    public void openToRead() throws FileNotFoundException {
        InputStream fis = new FileInputStream(file);
        br = new BufferedReader(new InputStreamReader(fis));
    }
    
    /** Appends a string to the file previously opened */
    public void append(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }
    
    /** Writes a string to the file previously opened (overwrites content */
    public void write(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }
    
    /** Return a line from the file previously opened */
    public String readLine() throws IOException {
        return br.readLine();
    }
    
    /** Deletes content from the file previously opened */
    public void deleteContent() throws FileNotFoundException {
        FileOutputStream writer = new FileOutputStream(file);

    }
}
