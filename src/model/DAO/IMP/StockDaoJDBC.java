package model.DAO.IMP;

import Store.Stock;
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
            String names[] = {"RTX 3060 ti 12gb", "Ryzen 7 2.5hz", "i5 9400f", "Gaming chair", "Headset HyperX", "Mechanic keyboard"};
            String categories[] = {"Electronics", "Electronics", "Electronics", "Comfort", "Peripherals", "Peripherals"};
            double prices[] = {3500.00, 600.00, 980.00, 600.00, 580.00, 260.00};
            int quantity[] = {25, 20, 10, 20, 10, 20};
            int rowsAffected = 0;

            for (int i = 0; i < names.length; i++) {
                if (findByName(names[i]) == null) {
                    st = conn.prepareStatement("INSERT INTO stock (name, category, price, quantity) VALUES (?, ?, ?, ?)");
                    st.setString(1, names[i]);
                    st.setString(2, categories[i]);
                    st.setDouble(3, prices[i]);
                    st.setInt(4, quantity[i]);
                    rowsAffected += st.executeUpdate();
                }
            }

            if(rowsAffected > 0){
                System.out.println("Product registration successful!");
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
                System.out.printf("ID: %d, nome: %s, category: %s, price: %.2f, quantity: %d%n", id, name, category, price, quantity);
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Stock findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM stock WHERE name = ?");
            st.setString(1, name);
            rs = st.executeQuery();

            if (rs.next()) {
                return new Stock(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"), rs.getInt("quantity"));
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateStockQuantity(String productName, int quantityPurchased) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE stock SET quantity = quantity - ? WHERE name = ?");
            st.setInt(1, quantityPurchased);
            st.setString(2, productName);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Stock obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM stock WHERE name = ?");
            st.setString(1, "Mechanic keyboard");
            int rowsAffected = st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
