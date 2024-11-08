package model.DAO.IMP;

import Users.Client;
import db.DbException;
import model.DAO.ClientDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDaoJDBC implements ClientDAO {
    private final Connection conn;

    public ClientDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Client obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO user (name) VALUES (?)");
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User successfully registered! You may proceed to your shopping cart!");
            } else {
                System.out.println("Failed to register the customer!");
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
}
