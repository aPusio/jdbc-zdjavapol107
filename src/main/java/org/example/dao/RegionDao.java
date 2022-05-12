package org.example.dao;

import org.example.model.RegionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO - Data Access Object
public class RegionDao {
    //WSZYSTKIE OPERACJE NA REGIONIE
    //CRUD - Create Read Update Delete
    private final Connection connection;

    public RegionDao(Connection connection) {
        this.connection = connection;
    }

    public RegionEntity getRegionById(Integer id) throws SQLException {
        PreparedStatement regionsStatement = connection.prepareStatement(
                "SELECT region_id, region_name FROM regions WHERE region_id = ?");
        regionsStatement.setInt(1, id);
        ResultSet resultSet = regionsStatement.executeQuery();
        if(resultSet.next()) {
            RegionEntity regionEntity = new RegionEntity(
                    resultSet.getInt("region_id"),
                    resultSet.getString("region_name"));
            regionsStatement.close();
            return regionEntity;
//            System.out.println(resultSet.getInt("region_id"));
//            System.out.println(resultSet.getString("region_name"));
        }
        regionsStatement.close();
        return null;
    }

    public List<RegionEntity> getAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT region_id, region_name FROM regions");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RegionEntity> regions = new ArrayList<>();
        while (resultSet.next()){
            RegionEntity regionEntity = new RegionEntity(
                    resultSet.getInt("region_id"),
                    resultSet.getString("region_name")
            );
            regions.add(regionEntity);
        }
        preparedStatement.close();
        return regions;
    }

    public void save(RegionEntity region) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO regions ( region_name ) VALUES (?)");
        preparedStatement.setString(1, region.getRegionName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
