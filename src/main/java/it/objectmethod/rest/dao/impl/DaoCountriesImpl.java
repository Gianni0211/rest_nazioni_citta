package it.objectmethod.rest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.rest.dao.IDaoCountries;
import it.objectmethod.rest.model.Country;

@Component

public class DaoCountriesImpl implements IDaoCountries {

	@Override
	public List<Country> getCountriesByContinent(String continent) {
		Connection conn = DBConnection.getConnection();
		List<Country> list = new ArrayList<Country>();
		String sql = "SELECT *  FROM country  WHERE Continent = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, continent);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setName(rs.getString("Name"));
				country.setCode(rs.getString("Code"));
				country.setContinent(rs.getString("Continent"));
				country.setPopulation(rs.getLong("Population"));
				country.setRegion(rs.getString("Region"));
				list.add(country);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Country> getAllCountries() {
		Connection conn = DBConnection.getConnection();
		List<Country> list = new ArrayList<Country>();
		String sql = "SELECT *  FROM country";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setName(rs.getString("Name"));
				country.setCode(rs.getString("Code"));
				country.setContinent(rs.getString("Continent"));
				country.setPopulation(rs.getLong("Population"));
				country.setRegion(rs.getString("Region"));
				list.add(country);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getAllContinents() {
		Connection conn = DBConnection.getConnection();
		List<String> list = new ArrayList<String>();
		String sql = "SELECT DISTINCT ct.Continent FROM country AS ct";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Continent"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

}
