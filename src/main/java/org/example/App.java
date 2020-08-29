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

        RegionDao regionDao = new RegionDao(connection);
        List<Region> regions = regionDao.getAll();
        regions.forEach(System.out::println);

        connection.close();
    }
}
