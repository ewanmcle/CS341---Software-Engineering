package ewan;

import java.util.LinkedList;

public class SalesSlip {

    LinkedList<SalesItem> ss = new LinkedList<SalesItem>();

    public void add(String n, int q, double p){

        SalesItem si = new SalesItem(n, q, p);
        ss.add(si);

    }

    public double computeSales(){
        
        Object[] arr = ss.toArray();
        double total = 0;

        for (int i = 0; i < arr.length; i++) {
            Object s = arr[i];
            double price = ((SalesItem) s).getPrice();
            total += price;
        }

        return total;
    }

    public String toString(){

        Object[] arr = ss.toArray();
        String o = "";

        for (int i = 0; i < arr.length; i++) {
            o += ((SalesItem) arr[i]).toString() + "\n";
        }

        return o;

    }

}
