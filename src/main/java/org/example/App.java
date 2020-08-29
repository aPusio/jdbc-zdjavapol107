package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
//        DbCreator dbCreator = new DbCreator();
//        dbCreator.createAndLoadData();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT REGION_NAME FROM REGIONS");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String regionName = resultSet.getString("REGION_NAME");
            System.out.println(regionName);
        }

        connection.close();
    }
}
