package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.sql.RegionSQL;
import org.example.models.Region;

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

	public int save(Region region) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(RegionSQL.INSERT_SQL);
		preparedStatement.setString(1, region.getRegionName());
		return preparedStatement.executeUpdate();
	}

	public int update(Region region) throws SQLException {
		String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, region.getRegionName());
		preparedStatement.setInt(2, region.getId());
		return preparedStatement.executeUpdate();
	}

	public int delete(int id) {
		String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";
		int i = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			i = preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return i;
	}

	public int transactionExample(int id) throws SQLException {
		connection.setAutoCommit(false);
		String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,"LALALA" );
			preparedStatement.setInt(2, 1);
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			preparedStatement2.setString(1,"HAHAHA" );
			preparedStatement2.setInt(2, 2);
			preparedStatement2.executeUpdate();

			connection.commit();
		} catch (SQLException throwables) {
			connection.rollback();
		}

	}

}
