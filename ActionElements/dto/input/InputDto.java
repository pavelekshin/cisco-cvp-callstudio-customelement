package com.audiumcorp.support.elements.actionElements.dto.input;

import com.audiumcorp.support.elements.actionElements.Utils;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputDto {
  
  public List<packServices> packServices = new ArrayList<>();
  
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }
  
  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
  
  public void validate() {
	 Utils.validateNotNull(this.packServices, "packServices");
	 Utils.validateIsNotEmpty(this.packServices, "packServices");
  }
}
