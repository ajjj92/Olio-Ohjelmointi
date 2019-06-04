package T5;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
    04-Jun-19
    @AtteJantunen
*/

public class ReadAndWriteIO {

    public ReadAndWriteIO() {

    }

    public void readZip() {
        String filename = "C:\\Users\\Atte\\Documents\\GitHub\\Olio-Ohjelmointi\\Olio-Ohjelmointi\\Viikko_4\\T5\\src\\T5\\zipinput.zip";
        String line = null;

        try {
            ZipFile zf = new ZipFile(filename);
            Enumeration entries = zf.entries();

            while(entries.hasMoreElements()) {

                ZipEntry ze = (ZipEntry) entries.nextElement();
                // find the next element in zip
                BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                // Buffered reader gets sent input stream from the data from zip file entry

                while((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }



        } catch(IOException i) {
            System.out.println("error");
            i.printStackTrace();
        }

    }
}
