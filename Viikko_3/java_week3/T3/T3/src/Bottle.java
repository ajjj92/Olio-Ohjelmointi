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
    private String company;
    private String name;
    private Float size;
    private Float price;
    
    public Bottle() {
        company = "Pepsi";
        name = "Pepsi Max";
        size = 0.3f;
        price = 1.80f;
        
}
    
    public String getName() {
        return name;
    }
    
    public String getCompany() {
        return company;
    }
    
    public Float getSize() {
        return size;
    }
    
    public Float getPrice()  {
        return price;
    }
    
}
