package it.objectmethod.rest.dao;

import java.util.List;

import it.objectmethod.rest.model.City;

public interface IDaoCities {

	public List<City> getCitiesByCountryCode(String code);
	public List<City> getCityByNameAndCountry(String name, String code);
	public int updateCityById(long id, City city);
	public int addCity(City city); 
}
