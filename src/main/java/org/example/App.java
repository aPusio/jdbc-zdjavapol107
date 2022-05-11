package org.example;

import java.sql.*;

public class App
{
    public static void main( String[] args ) throws Exception {
//        DbCreator dbCreator = new DbCreator();
//        dbCreator.createAndLoadData();

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        printRegionOne(connection);
        printItalia(connection);

        connection.close();
    }

    private static void printItalia(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT country_id, country_name, region_id FROM countries WHERE country_id = ?");
        preparedStatement.setString(1, "IT");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println(resultSet.getString("country_id"));
            System.out.println(resultSet.getString("country_name"));
            System.out.println(resultSet.getInt("region_id"));
        }
        preparedStatement.close();
    }

    private static void printRegionOne(Connection connection) throws SQLException {
        PreparedStatement regionsStatement = connection.prepareStatement(
                "SELECT region_id, region_name FROM regions WHERE region_id = ?");
        regionsStatement.setInt(1, 1);
        ResultSet resultSet = regionsStatement.executeQuery();
        if(resultSet.next()) {
            System.out.println(resultSet.getInt("region_id"));
            System.out.println(resultSet.getString("region_name"));
        }
        regionsStatement.close();
    }

    public static void examples(Connection connection) throws SQLException {
        //STATEMENT EXAMPLE
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM *");
//        boolean execute = preparedStatement.execute();
//        preparedStatement.close();

        //DIFFERENCE BETWEEN STATEMENT and PREPARED STATEMENT
        Statement statement = connection.createStatement();
//        int id = 2;
        String id = "2";
        statement.execute("SELECT * FROM * WHERE ID = " + id);
        statement.close();

        //PRZEKAZYWANIE PARAMETROW

        //ZLE !!!!
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM * WHERE ID = " + id);
        preparedStatement.execute();
        preparedStatement.close();

        //DOBRZE !!
        PreparedStatement correctPreparedStatement = connection.prepareStatement("SELECT * FROM * WHERE ID = ? AND name = ?");
        correctPreparedStatement.setString(1, id);
        correctPreparedStatement.setString(2, "Adam");
        correctPreparedStatement.execute();
        correctPreparedStatement.close();
    }

}
