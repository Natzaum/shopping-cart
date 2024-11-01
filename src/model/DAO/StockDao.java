package model.DAO;

import Store.Stock;

public interface StockDao {
    void insert(Stock obj);
    void read(Stock obj);
    Stock findByName(String name);
    void updateStockQuantity(String productName, int quantityPurchased);
    void delete(Stock obj);
}
