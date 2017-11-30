package com.geog.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
@FacesValidator("addDataValidator")

public class addDataValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {	
		FacesMessage message;
		
		// validate country code from different controls
		// adding the new country data
		if (component.getClientId().contains("countryCodeFromCountry")) {
			message = new FacesMessage("Error: Country Code: " + value.toString() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		// adding the new region data
		else if (component.getClientId().contains("countryCodeFromRegion")) {
			message = new FacesMessage("Error: Country Code: " + value.toString() + " does not exist");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		// adding the new city data
		else if (component.getClientId().contains("countryCodeFromCity")) {			
			Object cityCodeValue = ((UIInput) context.getViewRoot().findComponent("form:cityCode")).getSubmittedValue();
			if(cityCodeValue == null) {
				cityCodeValue = ((UIInput) context.getViewRoot().findComponent("form:cityCode")).getLocalValue();
			}
	        Object regionCodeValue = ((UIInput) context.getViewRoot().findComponent("form:regionCode")).getSubmittedValue();
	        
			message = new FacesMessage("Error: attempting to add Country: " + value.toString()
					+ ", Region: " + regionCodeValue.toString()
					+ " and City: " + cityCodeValue.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		// adding the new state data
		else if (component.getClientId().contains("countryCodeFromState")) {
			message = new FacesMessage("Error: Country Code: " + value.toString() + " does not exist");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
}
