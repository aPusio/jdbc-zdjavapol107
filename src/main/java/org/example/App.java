package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException {
//        DbCreator dbCreator = new DbCreator();
//        dbCreator.createAndLoadData();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT REGION_ID, REGION_NAME FROM REGIONS");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Region> regions = new ArrayList<>();

        while (resultSet.next()){
            String regionName = resultSet.getString("REGION_NAME");
            int regionId = resultSet.getInt("REGION_ID");
            Region region = new Region(regionId, regionName);
            regions.add(region);
        }
        regions.forEach(System.out::println);

        connection.close();
    }
}
