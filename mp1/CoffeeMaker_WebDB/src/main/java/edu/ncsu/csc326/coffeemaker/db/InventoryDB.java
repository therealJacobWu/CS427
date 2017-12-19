package edu.ncsu.csc326.coffeemaker.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ncsu.csc326.coffeemaker.Inventory;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class InventoryDB {
    public static Inventory checkInventory() throws InventoryException {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        Inventory i = null;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM inventory");
            ResultSet rs = stmt.executeQuery();
            //Only one result b/c name is primary key
            if (rs.next()) {
                i = new Inventory();
                i.setCoffee(Integer.parseInt(rs.getString("coffee")));
                i.setMilk(Integer.parseInt(rs.getString("milk")));
                i.setSugar(Integer.parseInt(rs.getString("sugar")));
                i.setChocolate(Integer.parseInt(rs.getString("chocolate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, stmt);
        }
        return i;
    }

    public static boolean addInventory(Inventory i) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean inventoryAdded = false;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("INSERT INTO inventory VALUES(?,?,?,?)");
            stmt.setInt(1, i.getCoffee());
            stmt.setInt(2, i.getMilk());
            stmt.setInt(3, i.getSugar());
            stmt.setInt(4, i.getChocolate());
            int updated = stmt.executeUpdate();
            if (updated == 1) {
                inventoryAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, stmt);
        }
        return inventoryAdded;
    }

    public static void useInventory(Recipe r) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("UPDATE inventory SET coffee=?, milk=?, sugar=?, chocolate=?");
            ResultSet rs = stmt.executeQuery();
            stmt.setInt(1, Integer.parseInt(rs.getString("coffee"))-r.getAmtCoffee());
            stmt.setInt(2, Integer.parseInt(rs.getString("milk"))-r.getAmtMilk());
            stmt.setInt(3, Integer.parseInt(rs.getString("sugar"))-r.getAmtSugar());
            stmt.setInt(4, Integer.parseInt(rs.getString("chocolate"))-r.getAmtChocolate());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, stmt);
        }
        return;
    }
}
