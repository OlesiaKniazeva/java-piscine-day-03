package ex03;

import com.sun.jndi.toolkit.url.UrlUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Downloader {

    List<String> urls;

    Downloader(Path pathToFile) {
        urls = new ArrayList<>();
        checkIfExist(pathToFile);
    }

    public void start() {
        int id = Integer.parseInt(Thread.currentThread().getName());

        if (id > urls.size()) {

        }
//        for (String s : urls) {
//            try {
//                URL url = new URL(s);
//                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
//                FileOutputStream fos = new FileOutputStream(Paths.get(s).getFileName().toString());
//                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//                fos.flush();
//                fos.close();
//                rbc.close();
//            } catch (IOException e) {
//                System.out.println("Couldn't download from URL: " + s);
//                continue;
//            }
//        }
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

    public void printList() {
        System.out.println("-----------------");
        for (String data : urls) {
            System.out.println('\'' + data + '\'');
        }
        System.out.println("-----------------");
    }

}
