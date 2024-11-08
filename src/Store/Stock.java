package Store;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable {
    int ID, quantity;
    String name, category;
    double price = 0;

    public Stock(int id, String name, String category, double price, int quantity) {
        this.ID = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public Stock() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "category='" + category + '\'' +
                ", ID=" + ID +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return ID == stock.ID && quantity == stock.quantity && Double.compare(price, stock.price) == 0 && Objects.equals(name, stock.name) && Objects.equals(category, stock.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, quantity, name, category, price);
    }

}
