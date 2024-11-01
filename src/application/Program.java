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
        Stock st = new Stock();

        System.out.println("Registre seu nome de usuario: ");
        String name = sc.nextLine();

        if (stockDao.findByName(st.getName()) == null) {
            stockDao.insert(st);
        }

        String prodName;
        while (true) {
            System.out.println("Produtos disponíveis:");
            stockDao.read(st);

            System.out.print("Digite o nome do produto desejado (ou 'sair' para finalizar a compra, 'remover' para remover um item do carrinho): ");
            prodName = sc.nextLine();

            if (prodName.equalsIgnoreCase("sair")) {
                System.out.println("Nos vemos na próxima!");
                break;
            }

            if (prodName.equalsIgnoreCase("remover")) {
                System.out.print("Digite o nome do item que deseja remover do carrinho: ");
                String itemName = sc.nextLine();

                cartDao.deleteByName(itemName);
                double totalCartValue = cartDao.calculateTotalValue();
                cartDao.read(shC);
                System.out.printf("Valor total do carrinho: R$ %.2f%n", totalCartValue);
                continue;
            }

            System.out.print("Qual a quantidade desejada: ");
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
                    System.out.println(prodQnt + " unidade de " + prodName + " adicionada com sucesso!");
                } else {
                    System.out.println(prodQnt + " unidades de " + prodName + " adicionadas com sucesso!");
                }

                double totalCartValue = cartDao.calculateTotalValue();
                cartDao.read(shC);
                System.out.printf("Valor total do carrinho: R$ %.2f%n", totalCartValue);

            } else {
                System.out.println("Falha ao adicionar o produto.");
            }
        }

        Client cl = new Client(name);

        // stockDao.delete(st);

        clientDAO.insert(cl);

        DB.closeConnection();
        sc.close();
    }
}
