/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */


public class Bottle {
  
    private String name;
    private Float size;
    private Float price;
    
    public Bottle(String name, Float size, Float price) {
        
        this.name = name;
        this.size = size;
        this.price = price;
        
}
    
    public String getName() {
        return name;
    }
    
    public Float getSize() {
        return size;
    }
    
    public Float getPrice()  {
        return price;
    }
    
}
