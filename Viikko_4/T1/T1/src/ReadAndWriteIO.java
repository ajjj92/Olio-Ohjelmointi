
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String line = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            while((line = in .readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException i) {
            System.out.println("error");
            i.printStackTrace();
        }
        
    }
}
