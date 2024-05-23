package com.audiumcorp.support.elements.actionElements.dto.input;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class packServices  {
  @JsonIgnore
  private String packServiceName;
  private String packServiceId;
  private String startDate;
  private String endDate;
  private String status;
  private String resourceNumber;
  private String serviceLogin;
  private String serviceName;
  private String srlsId;
  private String tariffPlan;
  private String lastUpdateDate;
  private String changedName;
    
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }
  
  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}
