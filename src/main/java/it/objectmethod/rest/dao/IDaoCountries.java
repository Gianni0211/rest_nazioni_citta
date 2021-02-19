package it.objectmethod.rest.dao;

import java.util.List;

import it.objectmethod.rest.model.Country;

public interface IDaoCountries {

	public List<Country> getCountriesByContinent(String continent);
	public List<Country> getAllCountries();
	public List<String> getAllContinents();
}
