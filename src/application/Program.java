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

        ShoppingCart shC = new ShoppingCart();

        System.out.println("Register your username: ");
        String name = sc.nextLine();
        Client cl = new Client(name);
        Stock st = new Stock();

        clientDAO.insert(cl);

        if (stockDao.findByName(st.getName()) == null) {
            stockDao.insert(st);
        }

        String prodName;
        while (true) {
            System.out.println("Available products:");
            stockDao.read(st);

            System.out.print("Enter the desired product name (or 'exit' to complete the purchase, 'remove' to remove an item from the cart): ");
            prodName = sc.nextLine();

            if (prodName.equalsIgnoreCase("exit")) {
                System.out.println("See you next time!");
                break;
            }

            if (prodName.equalsIgnoreCase("remove")) {
                System.out.print("Enter the name of the item you want to remove from the cart: ");
                String itemName = sc.nextLine();

                cartDao.deleteByName(itemName);
                double totalCartValue = cartDao.calculateTotalValue();
                cartDao.read(shC);
                System.out.printf("Total cart value: R$ %.2f%n", totalCartValue);
                continue;
            }

            System.out.print("Desired quantity: ");
            int prodQnt = sc.nextInt();
            sc.nextLine();

            Stock stockItem = stockDao.findByName(prodName);

            if (stockItem != null) {
                double totalValue = stockItem.getPrice() * prodQnt;

                ShoppingCart cartItem = new ShoppingCart(
                        stockItem.getID(),
                        prodQnt,
                        totalValue,
                        stockItem.getPrice(),
                        stockItem.getName(),
                        stockItem.getCategory()
                );

                cartDao.insert(cartItem);
                stockDao.updateStockQuantity(prodName, prodQnt);

                if (prodQnt == 1) {
                    System.out.println(prodQnt + " unity of " + prodName + " successfully added!");
                } else {
                    System.out.println(prodQnt + " units of " + prodName + " successfully added!");
                }

                double totalCartValue = cartDao.calculateTotalValue();
                cartDao.read(shC);
                System.out.printf("Total cart value: R$ %.2f%n", totalCartValue);

            } else {
                System.out.println("Failed to add the product.");
            }
        }

        DB.closeConnection();
        sc.close();
    }
}