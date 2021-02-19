package it.objectmethod.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.dao.IDaoCities;
import it.objectmethod.rest.model.City;

@RestController
public class CityController {

	@Autowired
	private IDaoCities dao;

	@GetMapping("/getcities/{code}")
	public List<City> getCitiesByCountryCode(@PathVariable("code") String code) {
		List<City> list = dao.getCitiesByCountryCode(code);
		return list;
	}

	@PostMapping("/getcity")
	public List<City> returnCityByNameAndCountry(@RequestParam("name") String name, @RequestParam("code") String code) {
		List<City> list = dao.getCityByNameAndCountry(name, code);
		return list;
	}

	@PostMapping("/updatecity")
	public String updateCity(@RequestBody City city) {
		int res = 0;

		if (city.getId() == 0) {

			res = dao.addCity(city);
		} else {
			city.setId(city.getId());
			res = dao.updateCityById(city.getId(), city);
		}

		return "Città salvate " + res;
	}

}
