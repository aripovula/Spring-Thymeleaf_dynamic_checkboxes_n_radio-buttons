package com.chc.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


// I almost gave up trying. But this really saved me.
// If not this I would not be able to finalyze this app.
//    https://stackoverflow.com/a/34061723/6332774
// Thank you  Tobias Liefke  !

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> list) {
    return String.join(",", list); 
  }

  @Override
  public List<String> convertToEntityAttribute(String joined) {
	  if (joined==null) return Arrays.asList();
    return new ArrayList<>(Arrays.asList(joined.split(",")));
  }

}