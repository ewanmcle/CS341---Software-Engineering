package ewan;

public class SalesItem {

    private String name;
    private int quantity;
    private double price;

    public SalesItem(String n, int q, double p){

        name = n;
        quantity = q;
        price = p;

    }
    
    // setters and getters

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }
    
    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int q){
        quantity = q;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double p){
        price = p;
    }

    public String toString(){
        String o = String.format("%-20s $%7.2f %5d", name, price, quantity);
        return o;
    }
    

}







