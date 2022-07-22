package ex03;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Links {

    ArrayList<String> urls;

    Links(Path pathToFile) {
        urls = new ArrayList<>();
        checkIfExist(pathToFile);
    }

    private void checkIfExist(Path path) {
        if (Files.exists(path) && Files.isReadable(path) && Files.isRegularFile(path)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.length() > 0) {
                        urls.add(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error with file");
                System.exit(1);

            }
        }
    }

    public int getLinksAmount() {
        return urls.size();
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void printList() {
        System.out.println("-----------------");
        for (String data : urls) {
            System.out.println('\'' + data + '\'');
        }
        System.out.println("-----------------");
    }

}
