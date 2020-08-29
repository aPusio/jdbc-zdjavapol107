package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

public class RegionDao {
	private Connection connection;

	public RegionDao(Connection connection) {
		this.connection = connection;
	}

	public List<Region> getAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Region> regions = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT REGION_ID, REGION_NAME FROM REGIONS");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String regionName = resultSet.getString("REGION_NAME");
				int regionId = resultSet.getInt("REGION_ID");
				Region region = new Region(regionId, regionName);
				regions.add(region);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return regions;
	}

	public Optional<Region> get(int id) throws SQLException {
		String sql = "SELECT REGION_ID, REGION_NAME FROM REGIONS WHERE REGION_ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Region region = null;
		if (resultSet.next()) {
			int regionId = resultSet.getInt("REGION_ID");
			String regionName = resultSet.getString("REGION_NAME");
			region = new Region(regionId, regionName);
		}
		return Optional.ofNullable(region);
	}
}
