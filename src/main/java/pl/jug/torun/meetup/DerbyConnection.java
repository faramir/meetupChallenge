/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.jug.torun.meetup.model.GiveAway;

/**
 *
 * @author faramir
 */
public class DerbyConnection {

    public DerbyConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initialize() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:meetupDatabase;create=true")) {
            Statement statement = conn.createStatement();
            statement.execute(""
                    + "CREATE TABLE giveAways ("
                    + "     id   INTEGER NOT NULL "
                    + "            PRIMARY KEY GENERATED ALWAYS AS IDENTITY "
                    + "            (START WITH 1, INCREMENT BY 1),"
                    + "     name VARCHAR(64) NOT NULL DEFAULT ''"
                    + ")");
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    protected List<GiveAway> getGiveAwaysList() {
        List<GiveAway> giveAwaysList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:derby:meetupDatabase")) {
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT id,name FROM giveAways");
            while (results.next()) {
                giveAwaysList.add(new GiveAway(results.getInt("id"), results.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return giveAwaysList;
    }
    
    protected GiveAway defineGiveAway(String name) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:meetupDatabase")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO giveAways(name) VALUES(?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, name);
            stmt.execute();
            ResultSet results = stmt.getGeneratedKeys();
            results.next();
            return new GiveAway(results.getInt(1), name);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    protected void deleteDefinedGiveAway(GiveAway giveAway) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:meetupDatabase")) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM giveAways WHERE id=? AND name=?");
            stmt.setInt(1, giveAway.getId());
            stmt.setString(2, giveAway.getName());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }        
    }

    protected void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:meetupDatabase;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
}
