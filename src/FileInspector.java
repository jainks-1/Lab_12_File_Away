import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir") + "/src"); // Corrected path

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file)); // Removed CREATE

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                while ((rec = reader.readLine()) != null) { // Changed reader.ready() to readLine() != null
                    lineCount++;
                    charCount += rec.length();
                    String[] words = rec.split("\\s+");
                    wordCount += words.length;

                    System.out.printf("\nLine %4d %-60s ", lineCount, rec); // Echo each line
                }

                System.out.println("\n\n--- Summary Report ---");
                System.out.println("File: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);

                reader.close();
                System.out.println("\n\nData file read!");
            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}