package model.DAO.IMP;

import Store.Stock;
import com.mysql.cj.protocol.Resultset;
import db.DbException;
import model.DAO.StockDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDaoJDBC implements StockDao {
    private Connection conn;

    public StockDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Stock obj) {
        PreparedStatement st = null;
        try {
            String names[] = {"RTX 3060 ti 12gb", "Ryzen 7 2.5hz", "i5 9400f", "Cadeira Gamer", "Headset HyperX", "Teclado mecanico"};
            String categories[] = {"Eletronicos", "Eletronicos", "Eletronicos", "Conforto", "Perifericos", "Perifericos"};
            double prices[] = {3500.00, 600.00, 980.00, 600.00, 580.00, 260.00};
            int quantity[] = {25, 20, 10, 3, 10, 20};
            int rowsAffected = 0;

            for(int i = 0; i < names.length; i++) {
                st = conn.prepareStatement("INSERT INTO stock (name, category, price, quantity) VALUES (?, ?, ?, ?)");
                st.setString(1, names[i]);
                st.setString(2, categories[i]);
                st.setDouble(3, prices[i]);
                st.setInt(4, quantity[i]);
                rowsAffected += st.executeUpdate();
            }

            if(rowsAffected > 0){
                System.out.println("Registro de produto realizado com sucesso!");
            }
            else{
                System.out.println("Falha ao registrar o produto.");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void read(Stock obj) {
        String query = "SELECT * FROM stock";

        try(PreparedStatement st = conn.prepareStatement(query)){
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("Quantity");
                System.out.printf("ID: %d, nome: %s, categoria: %s, preço: %.2f, quantidade: %d%n", id, name, category, price, quantity);
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Stock obj) {

    }

    @Override
    public void delete(Stock obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM stock WHERE name = ?");
            st.setString(1, "Teclado mecanico");
            int rowsAffected = st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
