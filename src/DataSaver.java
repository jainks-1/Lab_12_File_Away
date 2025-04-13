import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {
        ArrayList<String> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;
        int idCounter = 1; // Start ID counter at 1

        do {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter last name");
            String id = String.format("%06d", idCounter); // Format ID with leading zeros
            String email = SafeInput.getNonZeroLenString(in, "Enter email");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter year of birth", 1900, 2024); // Adjust range as needed

            String record = firstName + ", " + lastName + ", " + id + ", " + email + ", " + yearOfBirth;
            recs.add(record);

            idCounter++; // Increment ID counter

            done = SafeInput.getYNConfirm(in, "Add another record");
        } while (done);

        String fileName = "data.csv";
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "/src/" + fileName); // Save to src directory

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : recs) {
                writer.write(rec);
                writer.newLine();
            }

            writer.close();
            System.out.println("Data written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}