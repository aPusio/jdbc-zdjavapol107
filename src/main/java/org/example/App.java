package org.example;

import java.sql.Connection;
import java.sql.SQLException;

import org.example.dao.RegionDao;
import org.example.models.Region;

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

//        Optional<Region> region = regionDao.get(2);
//        region.ifPresent(System.out::println);

//        Region region = new Region("Arctic");
//        int amoutOfSavedRecords = regionDao.save(region);
//        System.out.println("saved: " + amoutOfSavedRecords + " records");
//
//        regionDao.getAll().forEach(System.out::println);

//        Optional<Region> region = regionDao.get(5);
        regionDao.update(new Region(4, "HAKUNA MATATA"));
        regionDao.getAll().forEach(System.out::println);
//

//        if(region.isPresent()){
//            Region regionFromDb = region.get();
//            regionFromDb.setRegionName("HAHAHHA");
//            regionDao.update(regionFromDb);
//        }
//
//        regionDao.getAll().forEach(System.out::println);

//        int amountOfDeletedRows = regionDao.delete(5);
//        System.out.println("deleted: " + amountOfDeletedRows);
//        regionDao.getAll().forEach(System.out::println);
//
//        Optional<Region> region2optional = regionDao.get(2);
//        Region region2 = region2optional.get();
//        System.out.println("region name: " + region2.getRegionName());
//
//        Optional<Region> region99optional = regionDao.get(99);
//        if(region99optional.isPresent()){
//            Region region99 = region99optional.get();
//        }
//

        connection.close();
    }
}
