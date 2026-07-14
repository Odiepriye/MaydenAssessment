
package application;

import java.util.Objects;

public class Grocery{
    final private double price;
    final private String name;
    public Grocery(double price, String name){
        this.price = price;
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(name.toLowerCase(), price);
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof Grocery)) return false;
        if(this == obj) return true;
        Grocery grocery = (Grocery) obj;
        return Double.compare(grocery.price, this.price) == 0 && Objects.equals(this.name.toLowerCase(), grocery.name.toLowerCase());
    }
}