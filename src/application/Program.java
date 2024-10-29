package application;

import Users.Client;
import db.DB;
import model.DAO.ClientDAO;
import model.DAO.DaoFactory;

import java.sql.Connection;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("Registre seu nome de usuario: ");
        String name = sc.nextLine();

        Client cl = new Client(name);

        ClientDAO clientDAO = DaoFactory.createClientDao();

        clientDAO.insert(cl);

        DB.closeConnection();
        sc.close();
    }
}
