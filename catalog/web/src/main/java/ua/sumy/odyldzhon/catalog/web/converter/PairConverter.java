package ua.sumy.odyldzhon.catalog.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.javatuples.Pair;

@FacesConverter("PairConverter")
public class PairConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
			String[] mAuthors = value.trim().split("\\s+");
			return Pair.with(mAuthors[0], mAuthors[1]);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		Pair pair = (Pair)value;
		return pair.getValue0()+" "+pair.getValue1();
	}

}
