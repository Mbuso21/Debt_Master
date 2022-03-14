package za.co.debtmaster.model;

import java.sql.*;
import java.util.Scanner;

public class WhatIsJdbc {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:chinook.db"; // /home/mbuso/Debt_Master/DebtMasterServer/mysqlsampledatabase.db
        Connection conn = connection(url);
//        queryStatement("select * from albums", conn, "title");
        preparedStatement(conn);
        queryStatement("select * from albums", conn, "title");
        while (true) {
            System.out.println("Enter Query: ");
            Scanner scanner = new Scanner(System.in);
            String columnQuery = scanner.nextLine();
            System.out.println(columnQuery);
            try {
                queryStatement("select * from albums", conn, columnQuery);
            } catch (SQLException e) {
                System.out.println("Not a valid query column");
            }


        }


//        preparedStatement(conn);
//        deleteRow(conn);
//        queryStatement("select * from albums", conn, "title");
    }

    public static Connection connection(String url) {
        Connection conn = null;
        try {
             // /home/mbuso/Debt_Master/DebtMasterServer/DebtMaster.db
            conn = DriverManager.getConnection(url);
            System.out.println("connected");
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return conn;
    }

    public static void queryStatement(String query, Connection connection, String columnLabel) throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println("Query executed");
            System.out.println(resultSet.next());
            while (resultSet.next()) {
                String name = resultSet.getString(columnLabel);
                System.out.println(name);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void preparedStatement(Connection connection) throws SQLException {

        String sql = "insert into albums (Title, ArtistId) values (?, ?);";

        PreparedStatement prepState  = connection.prepareStatement(sql);
        prepState.setString(1, "Uprising");
        prepState.setString(2, "hello");

        prepState.executeUpdate();

    }

    public static void deleteRow(Connection connection) throws SQLException {

        String sql = "delete from albums;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
