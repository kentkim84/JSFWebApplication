package com.geog.dao;

import java.util.List;
import com.geog.model.*;

public interface GeographyDao {
	// initialisation section
	public void initSqlConnection() throws Exception;	
	public void closeSqlConnection() throws Exception;
	// Country section	
	public List<Country> getAllCountries() throws Exception;
	public void addCountry(Country country) throws Exception;
	public void updateCountry(Country country, String srcCo_code) throws Exception;
	public void deleteCountry(Country country) throws Exception;
	// Region section
	public List<Region> getAllRegions() throws Exception;
	public void addRegion(Region region) throws Exception;
	public void updateRegion(Region region, String srcReg_code) throws Exception;
	public void deleteRegion(Region region) throws Exception;
	// City section
	public List<City> getAllCities() throws Exception;
	public List<Result> searchCities(City city, String condition) throws Exception;
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source) throws Exception;
	public void addCity(City city) throws Exception;
	public void updateCity(City city, String srcCty_code) throws Exception;
	public void deleteCity(City city) throws Exception;
}
