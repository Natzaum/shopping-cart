package model.DAO;

import db.DB;
import model.DAO.IMP.ClientDaoJDBC;

public class DaoFactory {
    public static ClientDAO createClientDao(){
        return new ClientDaoJDBC(DB.getConnection());
    }
}
