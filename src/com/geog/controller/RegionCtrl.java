package com.geog.controller;

import com.geog.dao.*;
import com.geog.model.*;

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
	private FacesMessage message;
	
	public RegionCtrl() {
	}
	
	public void onLoad(String page) {
		try {									
			if (page.equals("list")) {
				System.out.println("Page is: " + page);
				geographyDao = new GeographyDaoImpl();
				region = new Region();
				regionList = new ArrayList<Region>(geographyDao.getAllRegions());
			}
			else {
				System.out.println("Page is: " + page);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("SQLException") 
					&& (e.toString().contains("CommunicationsException")
					|| e.toString().contains("ConnectException")
					|| e.toString().contains("SocketException"))) {
				FacesMessage message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
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
			geographyDao = new GeographyDaoImpl();
			geographyDao.addRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// try to close the remaining connection
			// if still connected, system will close it
			try {
				geographyDao.closeSqlConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}
	
	// release constraint
	public String deleteRegion(Region region) {
		try {
			geographyDao = new GeographyDaoImpl();
			geographyDao.deleteRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// try to close the remaining connection
			// if still connected, system will close it
			try {
				geographyDao.closeSqlConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
		
	}
	
	// not required methods
	public String updateRegion() {		
		try {
			geographyDao = new GeographyDaoImpl();
			geographyDao.updateRegion(region);
			return "list_regions.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			// connection error handling
			if (e.toString().contains("CommunicationsException") 
					|| e.toString().contains("SocketException")
					|| e.toString().contains("ConnectException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);				
			}
			// sql update error handling
			else if (e.toString().contains("MySQLIntegrityConstraintViolationException")) {
				message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// other error handling
			// display the exception
			else {
				message = new FacesMessage("Error: " + e.toString());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			// try to close the remaining connection
			// if still connected, system will close it
			try {
				geographyDao.closeSqlConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
