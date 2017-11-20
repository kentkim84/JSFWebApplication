package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;

@ManagedBean
//@SessionScoped
@RequestScoped
public class RegionCtrl {
	private GeographyDao geographyDao = new GeographyDaoImpl();
	private List<Region> regionList;
	private Region region = new Region();
	private String returnMessage = new String();
	
	public RegionCtrl() {
	}
	
	public String getCo_code() {
		return region.getCo_code();
	}
	
	public void setCo_code(String co_code) {
		region.setCo_code(co_code);
	}
	
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
	
	public String getReturnMessage() {
		return returnMessage;
	}
	
	public void setReturnMessage(String errorMessage) {
		this.returnMessage = errorMessage;
	}
	
	public List<Region> getRegionList() {
		regionList = new ArrayList<Region>(geographyDao.getAllRegions());
		return regionList;
	}
	
	public String addRegion(Region region) {
		geographyDao.addRegion(region);
		return "list_regions.xhtml";
	}
	
	// release constraint
	public String deleteRegion(Region region) {	
		geographyDao.deleteRegion(region);
		return "list_regions.xhtml";
	}
	
	// not required methods
	public String updateRegion() {		
		geographyDao.updateRegion(region);
		return "list_regions.xhtml";
	}
	
	public String passValues(Region region) {
		this.region.setCo_code(region.getCo_code());
		this.region.setReg_code(region.getReg_code());
		this.region.setReg_name(region.getReg_name());
		this.region.setReg_details(region.getReg_details());
		return "update_region.xhtml";
	}
}
