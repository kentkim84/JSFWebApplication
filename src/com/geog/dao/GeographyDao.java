package com.geog.dao;

import java.util.List;
import com.geog.model.*;

public interface GeographyDao {
	// Country section
	public List<Country> getAllCountries();
	public Country getCountry(String code);
	public void addCountry(Country country);
	public void updateCountry(Country country);
	public void deleteCountry(Country country);
	// Region section
	public List<Region> getAllRegions();
	public Region getRegion(String code);
	public void addRegion(Region region);
	public void updateRegion(Region region);
	public void deleteRegion(Region region);
	// City section
	public List<City> getAllCities();
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source);
	public City getCity(String code);
	public void addCity(City city);
	public void updateCity(City city);
	public void deleteCity(City city);
	// State section
	/*public List<State> getAllStates();
	public State getState(String _id);
	public void addState(State state);
	public void updateState(State state);
	public void deleteState(State state);*/
}
