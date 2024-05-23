package com.audiumcorp.support.elements.actionElements;

import com.audiumcorp.support.elements.actionElements.dto.input.InputDto;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;

public class Utils {
  public static InputDto parseInputDto(String jsonString) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
      InputDto dto = (InputDto)mapper.readValue(jsonString, InputDto.class);
      dto.validate();
      return dto;
    } catch (IOException e) {
    	e.printStackTrace();
    } 
  }
  
  public static void validateNotNull(Object o, String objectName) {
    if (o == null)
      throw new Exception("CTI-002 Invalid value reference is null"); 
  }
  
  public static void validateIsNotEmpty(Collection<?> list, String name) {
    if (list.isEmpty())
      throw new Exception ("CTI-002 invalid reference value list is empty"); 
  }
}
