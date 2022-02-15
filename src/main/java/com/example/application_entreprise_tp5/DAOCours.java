package com.example.application_entreprise_tp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOCours {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public DAOCours(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = "";
    }
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertCours(Cours cours) throws SQLException {
        String sql = "INSERT INTO Cours (intitule, coef, duree) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, cours.getIntitule());
        statement.setInt(2, cours.getCoef());
        statement.setInt(3, cours.getDuree());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public Cours getCours(int id) throws SQLException {
        Cours cours = null;
        String sql = "SELECT * FROM Cours WHERE cours_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String intitule = resultSet.getString("intitule");
            int coef = resultSet.getInt("coef");
            int duree = resultSet.getInt("duree");

            cours = new Cours(id, intitule, coef, duree);
        }

        resultSet.close();
        statement.close();

        return cours;
    }

    public boolean deleteCours(Cours cours) throws SQLException {
        String sql = "DELETE FROM Cours where cours_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, cours.getCours_id());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Cours cours) throws SQLException {
        String sql = "UPDATE Cours SET intitule = ?, coef = ?, duree = ?";
        sql += " WHERE cours_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, cours.getIntitule());
        statement.setInt(2, cours.getCoef());
        statement.setInt(3, cours.getDuree());
        statement.setInt(4, cours.getCours_id());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public List<Cours> listAllCours() throws SQLException {
        List<Cours> listCours = new ArrayList<>();
        String sql = "SELECT * FROM Cours";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("cours_id");
            String intitule = resultSet.getString("intitule");
            int coef = resultSet.getInt("coef");
            int duree = resultSet.getInt("duree");
            Cours cours = new Cours(id, intitule, coef, duree);
            listCours.add(cours);
        }
        resultSet.close();
        statement.close();
        return listCours;
    }

}
