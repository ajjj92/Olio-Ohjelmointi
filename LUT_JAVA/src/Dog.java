
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
public class Dog {
    
    private String name;
    private String lause;
   
    
    public Dog() {
        name = "Doge";
        System.out.println("Hei, nimeni on "+name);
    }
    public Dog(String koira) {
        if(koira.trim().isEmpty()){
            name = "Doge";
            //do nothing
        } else {
            name = koira;
            System.out.println("Hei, nimeni on "+name);
    }
    }
    
    public void isInteger(String element) {
        try {
                Integer.parseInt(element);
                System.out.println(String.format("Such integer: %s", element));
               }catch(Exception error ){
                    if(element.equals("true") || element.equals("false")){
                        System.out.println(String.format("Such boolean: %s", element));
                }else{
                    System.out.println(String.format("%s", element));
                        }
                   
    }
    }
    

    
   public void Speak() {
       lause = "Much wow!";
       System.out.println(name+": "+lause);
       
    }
   public void Speak(String puhu) {
       if(puhu.trim().isEmpty()) {
           Scanner scan = new Scanner(System.in);
           lause = "Much wow!";
           System.out.println(name+": "+lause);
           System.out.print("Mitä koira sanoo: ");
           lause = scan.nextLine();
           Speak(lause);
       } else {
            String [] lista = puhu.split(" ");
            for(int i = 0; i<lista.length; i++){
               
                isInteger(lista[i]);
               }
               }
        
               }
   
   public String getName(){
       return name;
   }
 
}
