package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class RegionCtrl {
	private GeographyDao geographyDao;
	private List<Region> regionList;
	private Region region;	
	
	public RegionCtrl() {
	}
	
	public void onload() {
		try {
			region = new Region();
			geographyDao = new GeographyDaoImpl();			
			regionList = new ArrayList<Region>(geographyDao.getAllRegions());		
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Connection") || e.getMessage().contains("Communication")) {
				FacesMessage message = new FacesMessage("Error: SQL Database Connection Failed");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}		
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

	public List<Region> getRegionList() {
		return regionList;
	}
	
	public String addRegion(Region region) {
		try {
			geographyDao.addRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// release constraint
	public String deleteRegion(Region region) {
		try {
			geographyDao.deleteRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	// not required methods
	public String updateRegion() {		
		try {
			geographyDao.updateRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String passValues(Region region) {
		this.region.setCo_code(region.getCo_code());
		this.region.setReg_code(region.getReg_code());
		this.region.setReg_name(region.getReg_name());
		this.region.setReg_details(region.getReg_details());
		return "update_region.xhtml";
	}
}
