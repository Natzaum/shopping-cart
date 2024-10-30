package application;

import Store.Stock;
import Users.Client;
import db.DB;
import model.DAO.ClientDAO;
import model.DAO.DaoFactory;
import model.DAO.StockDao;

import java.sql.Connection;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        Scanner sc = new Scanner(System.in);

        //System.out.println("Registre seu nome de usuario: ");
        //String name = sc.nextLine();

        //Client cl = new Client(name);
        Stock st = new Stock();

        //ClientDAO clientDAO = DaoFactory.createClientDao();
        StockDao stockDao = DaoFactory.createStockDao();

        //stockDao.insert(st);
        stockDao.read(st);
        //clientDAO.insert(cl);

        DB.closeConnection();
        sc.close();
    }
}
