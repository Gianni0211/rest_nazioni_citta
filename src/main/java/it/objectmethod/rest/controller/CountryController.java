package it.objectmethod.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.dao.IDaoCountries;
import it.objectmethod.rest.model.Country;

@RestController
public class CountryController {

	@Autowired
	private IDaoCountries countryDao;

	@GetMapping("/getcontinents")
	public List<String> getContinents() {
		List<String> list = countryDao.getAllContinents();
		return list;
	}

	@GetMapping("/countries/{continent}")
	public List<Country> getCountries(@PathVariable String continent) {
		List<Country> list = countryDao.getCountriesByContinent(continent);
		return list;
	}
	
	@GetMapping("/allcountries")
	public List<Country> getAllCountries(){
		List<Country> list = countryDao.getAllCountries();
		return list;
	}

}
