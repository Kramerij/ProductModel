import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 4;

        String id, name, description;
        double cost;
try {
    File workingDirectory = new File(System.getProperty("user.dir"));
    chooser.setCurrentDirectory(workingDirectory);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        selectedFile = chooser.getSelectedFile();
        Path file = selectedFile.toPath();

        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int line = 0;
        while (reader.ready()) {
            rec = reader.readLine();
            lines.add(rec);
            line++;
        }
        reader.close();
        //file has been read

        //making the output table
        System.out.println("ID      Name       Description               Cost");
        System.out.println("===================================================");
        String[] fields;
        for (String a : lines) {
            fields = a.split(",", 0);

            if (fields.length == FIELDS_LENGTH) {
                id = fields[0].trim();
                name = fields[1].trim();
                description = fields[2].trim();
                cost = Double.parseDouble(fields[3].trim());
                System.out.printf("\n%-8s%-10s%-25s%-6s", id, name, description, cost);
            } else {
                System.out.println("File was corrupt");
                System.out.println(a);
            }
        }
    }
}
catch (FileNotFoundException e)
{
    System.out.println("File not found");
    e.printStackTrace();
}
catch (IOException e)
{
    e.printStackTrace();
}
}
}


