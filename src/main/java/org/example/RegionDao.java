package org.example;

import java.sql.Connection;
import java.util.List;

public class RegionDao {
	private Connection connection;

	public RegionDao(Connection connection) {
		this.connection = connection;
	}

	public List<Region> getAll(){


		return null;
	}
}
