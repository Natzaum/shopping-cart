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

        ClientDAO clientDAO = DaoFactory.createClientDao();
        StockDao stockDao = DaoFactory.createStockDao();
        CartDao cartDao = DaoFactory.createCartDao();

        Stock st = new Stock();

        //System.out.println("Registre seu nome de usuario: ");
        //String name = sc.nextLine();

        //stockDao.insert(st);

        String prodName;

        while(true) {
            System.out.println("Produtos disponiveis");
            stockDao.read(st);

            System.out.print("Digite o nome do produto desejado (ou 'sair' para finalizar a compra): ");
            prodName = sc.nextLine();

            if (prodName.equalsIgnoreCase("sair")) {
                System.out.println("Nos vemos na próxima!");
                break;
            }

            System.out.print("Qual a quantia desejada: ");
            int prodQnt = sc.nextInt();
            sc.nextLine();

            Stock stockItem = stockDao.findByName(prodName);

            if (stockItem != null) {
                double totalValue = stockItem.getPrice() * prodQnt;

                ShoppingCart cartItem = new ShoppingCart(stockItem.getID(),
                        prodQnt, totalValue, stockItem.getPrice(), stockItem.getName(),
                        stockItem.getCategory());

                cartDao.insert(cartItem);
                if(prodQnt == 1) {
                    System.out.println(prodQnt + " unidade de " + prodName + " adicionada com sucesso!");
                }
                else{
                    System.out.println(prodQnt + " unidades de " + prodName + " adicionadas com sucesso!");
                }
            }
            else{
                System.out.println("Falha ao adicionar o produto.");
            }
        }
        //Client cl = new Client(name);

        //stockDao.delete(st);
        //clientDAO.insert(cl);

        DB.closeConnection();
        sc.close();
    }
}

