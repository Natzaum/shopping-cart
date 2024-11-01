package model.DAO.IMP;

import Store.ShoppingCart;
import db.DbException;
import model.DAO.CartDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDaoJDBC implements CartDao {
    private Connection conn;
    public CartDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ShoppingCart obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO shoppingCart (stock_id, quantity, name, category, price, totalValue) VALUES (?, ?, ?, ?, ?, ?)");

            // Define os valores a serem inseridos
            st.setInt(1, obj.getStockID());
            st.setInt(2, obj.getQuantity());
            st.setString(3, obj.getName());
            st.setString(4, obj.getCategory());
            st.setDouble(5, obj.getPrice());

            // Calcula o totalValue como price * quantity
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
                System.out.println("Item atualizado com sucesso no carrinho!");
            } else {
                System.out.println("Item não encontrado para atualização.");
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
    public void delete(ShoppingCart obj) {

    }
}
