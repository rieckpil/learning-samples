package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.PhoneNumber;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "phoneNumberConverter")
public class PhoneNumberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }

        if (!value.matches("^[0-9]{10}")) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Phone number must have 10 digits !"));
        }

        PhoneNumber phoneNumber = new PhoneNumber(value.substring(0, 3), value.substring(3, 6), value.substring(6));
        return phoneNumber;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        PhoneNumber phoneNumber = (value != null) ? ((PhoneNumber) value) : null;
        return (phoneNumber != null) ? String.valueOf(phoneNumber.getAreacode()).concat(String.valueOf(phoneNumber.getExchange()).concat(String.valueOf(phoneNumber.getSubscriber()))) : "";
    }
}

