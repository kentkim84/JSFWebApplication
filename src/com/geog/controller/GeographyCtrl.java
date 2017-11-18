package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.RequestScoped;

@ManagedBean(name = "geography")
@SessionScoped
//@RequestScoped
public class GeographyCtrl {
	private GeographyDao geographyDao = new GeographyDaoImpl();
	private List<Country> countryList;
	private List<Region> regionList;
	private List<City> cityList;
	private Country country = new Country();
	private Region region = new Region();
	private City city = new City();
	
	public GeographyCtrl() {
		System.out.println("Geography called");
	}
	
	// Country control section	
	public String getCo_code() {
		return country.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		country.setCo_code(co_code);
	}
	
	public String getCo_name() {
		return country.getCo_name();
	}
	
	public void setCo_name(String co_name) {
		country.setCo_name(co_name);
	}
	
	public String getCo_details() {
		return country.getCo_details();
	}
	
	public void setCo_details(String co_details) {
		country.setCo_details(co_details);
	}
	
	public List<Country> getCountryList() {
		countryList = new ArrayList<Country>(geographyDao.getAllCountries());
		System.out.println("getCountryList called from GeographyCtrl");
		return countryList;
	}
	
	public String addCountry(Country country) {
		this.country = country;
		geographyDao.addCountry(this.country);
		System.out.println(this.country.getCo_code() 
				+ " " + this.country.getCo_name() 
				+ " " + this.country.getCo_details());
		return "list_countries.xhtml";
	}
	
	public String deleteCountry(Country country) {
		this.country = country;
		geographyDao.deleteCountry(country);
		System.out.println("deleteCountry called from GeographyCtrl");
		return "list_countries.xhtml";
	}
		
	public String updateCountry() {		
//		this.country = country;
		geographyDao.updateCountry(country);
		System.out.println("updateCountry called from GeographyCtrl");
		return "list_countries.xhtml";
	}
	
	public String passValues(Country country) {
		this.country.setCo_code(country.getCo_code());
		this.country.setCo_name(country.getCo_name());
		this.country.setCo_details(country.getCo_details());
		
		return "update_country.xhtml";
	}
	
	// Region control section
	/*public String getCo_code() {
		return country.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		country.setCo_code(co_code);
	}*/
	
	public String getReg_code() {
		return region.getReg_code();
	}
	
	public void setReg_code(String reg_code) {
		region.setReg_code(reg_code);
	}
	
	public String getReg_name() {
		return region.getReg_name();
	}
	
	public void setReg_name(String reg_name) {
		region.setReg_name(reg_name);
	}
	
	public String getReg_details() {
		return region.getReg_details();
	}
	
	public void setReg_details(String reg_details) {
		region.setReg_details(reg_details);
	}
	
	public List<Region> getRegionList() {
		regionList = new ArrayList<Region>(geographyDao.getAllRegions());
		System.out.println("getRegionList called");
		return regionList;
	}
	
	public String addRegion(Region region) {
		this.region = region;
		geographyDao.addRegion(this.region);
		System.out.println(this.region.getCo_code() 
				+ " " + this.region.getReg_code() 
				+ " " + this.region.getReg_name() 
				+ " " + this.region.getReg_details());
		return "list_regions.xhtml";
	}
	
	public String deleteRegion(Region region) {
		this.region = region;
		geographyDao.deleteRegion(region);
		System.out.println("deleteRegion called from GeographyCtrl");
		return "list_regions.xhtml";
	}
		
	public String updateRegion() {		
		geographyDao.updateRegion(region);
		System.out.println("updateRegion called from GeographyCtrl");
		return "list_regions.xhtml";
	}
	
	// City control section
	/*public String getCo_code() {
		return country.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		country.setCo_code(co_code);
	}
	
	public String getReg_code() {
		return region.getReg_code();
	}
	
	public void setReg_code(String reg_code) {
		region.setReg_code(reg_code);
	}*/
	
	public String getCty_code() {
		return city.getCty_code();
	}
	
	public void setCty_code(String cty_code) {
		city.setCty_code(cty_code);
	}
	
	public String getCty_name() {
		return city.getCty_name();
	}
	
	public void setCty_name(String cty_name) {
		city.setCty_name(cty_name);
	}
	
	public int getPopulation() {
		return city.getPopulation();
	}
	
	public void setPopulation(int population) {
		city.setPopulation(population);
	}
	
	public boolean getIsCoastal() {
		return city.getIsCoastal();
	}
	public void setIsCoastal(boolean isCoastal) {
		city.setIsCoastal(isCoastal);
	}
	
	public double getAreaKm() {
		return city.getAreaKm();
	}
	
	public void setAreaKm(double areaKm) {
		city.setAreaKm(areaKm);
	}
	
	public List<City> getCityList() {
		cityList = new ArrayList<City>(geographyDao.getAllCities());
		System.out.println("getCityList called");
		return cityList;
	}
		
	public String addCity(City city) {
		this.city = city;
		geographyDao.addRegion(this.city);
		System.out.println(this.city.getCty_code() 
				+ " " + this.city.getCo_code()
				+ " " + this.city.getReg_code()				
				+ " " + this.city.getCty_name()
				+ " " + this.city.getIsCoastal()
				+ " " + this.city.getAreaKm());
		return "list_cities.xhtml";
	}
	
	public String deleteCity(City city) {
		this.city = city;
		geographyDao.deleteCity(city);
		System.out.println("deleteCity called from GeographyCtrl");
		return "list_cities.xhtml";
	}
		
	public String updateCity() {		
		geographyDao.updateCity(city);
		System.out.println("updateCity called from GeographyCtrl");
		return "list_cities.xhtml";
	}


}
