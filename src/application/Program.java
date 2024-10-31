package application;

import Store.ShoppingCart;
import Store.Stock;
import Users.Client;
import db.DB;
import model.DAO.CartDao;
import model.DAO.ClientDAO;
import model.DAO.DaoFactory;
import model.DAO.IMP.CartDaoJDBC;
import model.DAO.StockDao;

import java.sql.Connection;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        Scanner sc = new Scanner(System.in);
        //ClientDAO clientDAO = DaoFactory.createClientDao();
        StockDao stockDao = DaoFactory.createStockDao();
        CartDao cartDao = DaoFactory.createCartDao();

        //System.out.println("Registre seu nome de usuario: ");
        //String name = sc.nextLine();

        Stock st = new Stock();

        stockDao.insert(st);
        System.out.println("Adicione itens ao carrinho:");
        stockDao.read(st);
        System.out.println("Digite o nome do produto desejado: ");
        String prodName = sc.nextLine();
        System.out.println("Qual a quantia desejada?");
        int prodQnt = sc.nextInt();

        Stock stockItem = stockDao.findByName(prodName);

        if (stockItem != null) {
            // Calcula o valor total (price * quantity)
            double totalValue = stockItem.getPrice() * prodQnt;

            // Cria o item do carrinho com todos os campos necessários
            ShoppingCart cartItem = new ShoppingCart(stockItem.getID(), prodQnt, totalValue, stockItem.getPrice(), stockItem.getName(), stockItem.getCategory());

            // Insere no carrinho
            cartDao.insert(cartItem);
        }


        sc.close();


        //Client cl = new Client(name);

        //stockDao.delete(st);
        //clientDAO.insert(cl);

        DB.closeConnection();
        sc.close();
    }
}
