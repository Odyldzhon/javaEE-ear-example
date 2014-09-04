package ua.sumy.odyldzhon.catalog.web.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.javatuples.Pair;

@Named("UrlConverter")
@RequestScoped
public class DataUrlConverter {
	
	@SuppressWarnings("rawtypes")
	public String convertAuthorsToURL(List<Pair> value){
    	List<Pair> authors = value;
    	StringBuilder stBuilder = new StringBuilder();
		for(Pair author: authors){
			stBuilder.append(author.getValue0());
			stBuilder.append(" ");
			stBuilder.append(author.getValue1());
			stBuilder.append(" ");
		}
		return stBuilder.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public List<Pair> convertAuthorsFromURL(String value){
		if(value == null)
			return null;
		ArrayList<Pair> selectedAuthors = new ArrayList<Pair>();
		String[] mAuthors = value.trim().split("\\s+");
		for(int i = mAuthors.length; i != 0; i-=2){
			Pair p = Pair.with(mAuthors[i-1], mAuthors[i-2]);
			selectedAuthors.add(p);
		}
		return selectedAuthors;
	}
	
	public String convertDateToURL(Date value){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(value);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}
	
	public Date convertDateFromURL(String value){
		try {
			return new SimpleDateFormat("yyyy").parse(value);
		} catch (Exception e) {
			return null;
		}
	}
}
