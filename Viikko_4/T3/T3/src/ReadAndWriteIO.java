
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
       
    }
    
    public void readAndWrite(String input, String output) {
        String line = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(input));
            BufferedWriter out = new BufferedWriter(new FileWriter(output));
            
            while((line = in .readLine()) != null) {
                
                if(line.trim().isEmpty()) {
                    //do nothing
                    
                }else if (line.length() < 30) {
                    out.write(line);
                    out.newLine();
            }
            }
            in.close();  
            out.close();
            
        } catch(IOException i) {
            System.out.println("error");
            i.printStackTrace();
        }
        
    }
}
