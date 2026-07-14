package application;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Application {
    private ArrayList<Grocery> groceryList;
    private Set<Grocery> crossedList;
    final private double limit;
    private double current;
    public Application(double limit){
        groceryList = new ArrayList<>();
        crossedList = new HashSet<Grocery>();
        this.limit = limit;
        this.current = 0;
    }
    public void viewList(){
        for(Grocery item : groceryList){
            if(crossedList.contains(item)){
                System.out.println(item.getName() + "*");
            }else{
                System.out.println(item.getName());
            }
        }
    }
    public boolean addToList(Grocery grocery, int position){
        if(groceryList.contains(grocery)){
            System.out.println("Item already in list");
            return false;
        }else{
            groceryList.add(position, grocery);
            current += grocery.getPrice();
            isOverLimit();
            return true;
        }
    }
    public boolean addToList(Grocery grocery){
        return addToList(grocery, this.groceryList.size());
    }
    public void removeFromList(Grocery grocery){
        if(!groceryList.contains(grocery)){
            System.out.println("Item not in list");
        }else{
            groceryList.remove(grocery);
            crossedList.remove(grocery);
            current -= grocery.getPrice();
        }
    }
    public void crossOffItem(Grocery grocery){
        crossedList.add(grocery);
    }
    public void reorderItems(Grocery grocery, int newPosition){
        groceryList.remove(grocery);
        groceryList.add(newPosition, grocery);
    }
    public double getCurrent(){
        return current;
    }
    public double getLimit(){
        return limit;
    }
    public boolean isOverLimit(){
        if (this.current > this.limit){
            System.out.println("Over Set Limit");
        }
        return this.current > this.limit;
    }
    public ArrayList<Grocery> getGroceryList(){
        return new ArrayList<>(groceryList);
    }
    public Set<Grocery> getCrossedList(){
        return new HashSet<>(crossedList);
    }
}
