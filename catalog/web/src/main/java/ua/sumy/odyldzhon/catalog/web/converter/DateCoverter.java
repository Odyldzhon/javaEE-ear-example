package ua.sumy.odyldzhon.catalog.web.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("DateConverter")
public class DateCoverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		try {
			return new SimpleDateFormat("yyyy").parse((String)value);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date)value);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

}
