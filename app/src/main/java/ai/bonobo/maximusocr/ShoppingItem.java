package ai.bonobo.maximusocr;

/**
 * Created by Cristi Arde on 9/9/2018.
 */

public class ShoppingItem {

    public ShoppingItem(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    private int id;
    private  String name;
    private double price;


}
