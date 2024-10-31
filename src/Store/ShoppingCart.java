package Store;

public class ShoppingCart {
    String name, category;
    double price, totalValue;
    int quantity, id, stockID;

    public ShoppingCart(int id, int stockID, double totalValue, int quantity, double price, String name, String category) {
        this.id = id;
        this.stockID = stockID;
        this.totalValue = totalValue;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public ShoppingCart(int stockID, int quantity, double totalValue, double price, String name, String category) {
        this.stockID = stockID;
        this.quantity = quantity;
        this.totalValue = totalValue;
        this.price = price;
        this.name = name;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public ShoppingCart(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
