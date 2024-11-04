package model.DAO.IMP;

import Store.ShoppingCart;
import db.DbException;
import model.DAO.CartDao;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDaoJDBC implements CartDao {
    private final Connection conn;

    public CartDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ShoppingCart obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO shoppingCart (stock_id, quantity, name, category, price, totalValue) VALUES (?, ?, ?, ?, ?, ?)");

            st.setInt(1, obj.getStockID());
            st.setInt(2, obj.getQuantity());
            st.setString(3, obj.getName());
            st.setString(4, obj.getCategory());
            st.setDouble(5, obj.getPrice());

            double totalValue = obj.getPrice() * obj.getQuantity();
            st.setDouble(6, totalValue);

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
    public void read(ShoppingCart obj) {
        String query = "SELECT name, quantity FROM shoppingcart";

        try (PreparedStatement st = conn.prepareStatement(query)){
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");

                System.out.printf("Cart items: %s - %d %n", name, quantity);
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(ShoppingCart obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE shoppingCart SET quantity = ?, totalValue = ? WHERE id = ?"
            );
            st.setInt(1, obj.getQuantity());
            st.setDouble(2, obj.getTotalValue());
            st.setInt(3, obj.getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item successfully updated in the cart!");
            } else {
                System.out.println("Item not found for update.");
            }
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
    public void deleteByName(String productName) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM shoppingCart WHERE name = ?");
            st.setString(1, productName);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item " + productName + " successfully removed from the cart!");
            } else {
                System.out.println("Item " + productName + " not found in the cart.");
            }

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
    public double calculateTotalValue() {
        double totalValue = 0;
        String query = "SELECT SUM(totalValue) AS total FROM shoppingCart";

        try (PreparedStatement st = conn.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalValue = rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return totalValue;
    }

}
