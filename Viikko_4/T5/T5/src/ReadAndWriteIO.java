
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
public class ReadAndWriteIO {
    
    public ReadAndWriteIO() {
       
    }
    
    public void readZip() {
        String filename = "C:\\Users\\Atte\\Documents\\GitHub\\Olio-Ohjelmointi\\Olio-Ohjelmointi\\Viikko_4\\T5\\T5\\zipinput.zip";
        String line = null;
        
        try {
            ZipFile zp = new ZipFile(filename);
            ZipEntry ze = zp.getEntry("testi.txt");
            System.out.println(ze);
             
        } catch(IOException i) {
            System.out.println("error");
            i.printStackTrace();
        }
        
    }
}
