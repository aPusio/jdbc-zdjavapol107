package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDao {
	private Connection connection;

	public RegionDao(Connection connection) {
		this.connection = connection;
	}

	public List<Region> getAll() throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT REGION_ID, REGION_NAME FROM REGIONS");
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Region> regions = new ArrayList<>();

		while (resultSet.next()) {
			String regionName = resultSet.getString("REGION_NAME");
			int regionId = resultSet.getInt("REGION_ID");
			Region region = new Region(regionId, regionName);
			regions.add(region);
		}
		return regions;
	}
}
