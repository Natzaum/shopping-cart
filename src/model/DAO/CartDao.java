package model.DAO;

import Store.ShoppingCart;

public interface CartDao {
    void insert(ShoppingCart obj);
    void read(ShoppingCart obj);
    void update(ShoppingCart obj);
    void deleteByName(String productName);
    double calculateTotalValue();
}
