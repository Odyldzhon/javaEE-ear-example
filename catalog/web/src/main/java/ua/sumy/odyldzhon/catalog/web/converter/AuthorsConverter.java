package ua.sumy.odyldzhon.catalog.web.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.javatuples.Pair;

@FacesConverter("AuthorsConverter")
public class AuthorsConverter implements Converter{

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value == null)
			return null;
		String authors = (String)value;
		ArrayList<Pair> selectedAuthors = new ArrayList<Pair>();
		String[] mAuthors = authors.trim().split("\\s+");
		for(int i = mAuthors.length; i != 0; i-=2){
			Pair p = Pair.with(mAuthors[i-2], mAuthors[i-1]);
			selectedAuthors.add(p);
		}
		return selectedAuthors;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value == null)
			return null;
    	List<Pair> authors = (List<Pair>)value;
    	StringBuilder stBuilder = new StringBuilder();
		for(Pair author: authors){
			stBuilder.append(author.getValue0());
			stBuilder.append(" ");
			stBuilder.append(author.getValue1());
			stBuilder.append(" ");
		}
		stBuilder.deleteCharAt(stBuilder.length()-1);
		return stBuilder.toString();
	}

}
