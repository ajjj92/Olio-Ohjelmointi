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
    }
    public Dog(String koira) {
        if(koira.trim().isEmpty()){
            name = "Doge";
            //do nothing
        } else
            name = koira;
    }
    
   public void Speak() {
       lause = "Much wow!";
       System.out.println(name+": "+lause);
    }
   public void Speak(String puhu) {
       if(puhu.trim().isEmpty()) {
           lause = "Much wow!";
           //do nothing
       } else {
            lause = puhu;
    }
        System.out.println(name+": "+lause);

   }
   public String getName(){
       return name;
   }
 
}
