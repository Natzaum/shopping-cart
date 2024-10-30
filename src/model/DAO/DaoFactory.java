package model.DAO;

import db.DB;
import model.DAO.IMP.ClientDaoJDBC;
import model.DAO.IMP.StockDaoJDBC;

public class DaoFactory {
    public static ClientDAO createClientDao(){
        return new ClientDaoJDBC(DB.getConnection());
    }

    public static StockDao createStockDao(){
        return new StockDaoJDBC(DB.getConnection());
    }
}
