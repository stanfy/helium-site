package com.stanfy.helium.server;

import com.google.gson.annotations.SerializedName;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Data {

  @Id
  public transient String id;

  @SerializedName("boolean_truth")
  private boolean boolData;

  @SerializedName("string_data")
  @Index
  private String text;

  @SerializedName("numeric_data")
  private Integer numericValue;

  public Data(final String key) {
    id = key;
  }

  public Data() { }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public boolean getBoolValue() {
    return boolData;
  }

  public String getText() {
    return text;
  }

  public void setNumericValue(Integer newValue) {
    numericValue = newValue;
  }

  public Integer getNumericValue() {
    return numericValue;
  }

  public void setBoolData(boolean boolData) {
    this.boolData = boolData;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "string = " + text + ", bool = " + boolData + ", number = " + numericValue.toString();
  }
}
