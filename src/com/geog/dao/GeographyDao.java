package com.geog.dao;

import java.util.List;
import com.geog.model.*;

public interface GeographyDao {
	// initialisation section
	public void initSqlConnection();
	public void initMongoConnection();
	// Country section	
	public List<Country> getAllCountries();
	public String addCountry(Country country);
	public String updateCountry(Country country, String srcCo_code);
	public String deleteCountry(Country country);
	// Region section
	public List<Region> getAllRegions();
	public String addRegion(Region region);
	public String updateRegion(Region region);
	public String deleteRegion(Region region);
	// City section
	public List<City> getAllCities();
	public List<Result> searchCities(City city, String condition);
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source);
	public String addCity(City city);
	public String updateCity(City city);
	public String deleteCity(City city);
	// State section
	public List<State> getAllStates();	
	public String addState(State state);	
	public String deleteState(State state);
}
