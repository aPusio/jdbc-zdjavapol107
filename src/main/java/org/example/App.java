package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App
{
    public static void main( String[] args ) throws SQLException {
//        DbCreator dbCreator = new DbCreator();
//        dbCreator.createAndLoadData();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        RegionDao regionDao = new RegionDao(connection);
//        List<Region> regions = regionDao.getAll();
//        regions.forEach(System.out::println);

        Optional<Region> region = regionDao.get(2);
        region.ifPresent(System.out::println);

        connection.close();
    }
}
