package it.objectmethod.rest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.rest.dao.IDaoCities;
import it.objectmethod.rest.model.City;

@Component
public class DaoCitiesImpl implements IDaoCities {

	@Override
	public List<City> getCitiesByCountryCode(String code) {
		Connection conn = DBConnection.getConnection();
		List<City> list = new ArrayList<>();
		String sql = "SELECT * FROM city AS c INNER JOIN country AS cn ON cn.Code = c.CountryCode WHERE c.CountryCode = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, code);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				City city = new City();
				city.setName(rs.getString("Name"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getLong("Population"));
				city.setId(rs.getLong("ID"));
				list.add(city);
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
	public List<City> getCityByNameAndCountry(String name, String code) {
		Connection conn = DBConnection.getConnection();
		List<City> list = new ArrayList<City>();
		String sql = "SELECT * FROM city  WHERE ( '' LIKE ? OR Name LIKE ? ) AND ( '' = ? OR CountryCode = ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (!name.equals("")) {
				name = name + "%";
			}
			stmt.setString(1, name);
			stmt.setString(2, name);
			stmt.setString(3, code);
			stmt.setString(4, code);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				City city = new City();
				city.setName(rs.getString("Name"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getLong("Population"));
				city.setId(rs.getLong("ID"));
				list.add(city);
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
	public int updateCityById(long id, City city) {
		int res = 0;
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCountryCode());
			stmt.setString(3, city.getDistrict());
			stmt.setInt(4, (int) city.getPopulation());
			stmt.setLong(5, id);
			res = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int addCity(City city) {
		int res = 0;
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO  city SET Name = ?, CountryCode = ?, District = ?, Population = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCountryCode());
			stmt.setString(3, city.getDistrict());
			stmt.setInt(4, (int) city.getPopulation());

			res = stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

}
