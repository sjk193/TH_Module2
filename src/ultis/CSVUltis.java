package ultis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUltis {
    public static List<String> read(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(fileName + " invalid");
        }
    }

    public static <T> void write(String fileName, List<T> items){
        try {
            PrintWriter printWriter = new PrintWriter(fileName);

            for(Object item : items){
                printWriter.write(item.toString());
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
