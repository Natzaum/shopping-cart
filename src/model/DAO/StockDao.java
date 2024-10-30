package model.DAO;

import Store.Stock;

public interface StockDao {
    void insert(Stock obj);
    void read(Stock obj);
    void update(Stock obj);
    void delete(Stock obj);
}
