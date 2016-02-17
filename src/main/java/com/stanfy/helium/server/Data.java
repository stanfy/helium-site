package com.stanfy.helium.server;

import com.google.gson.annotations.SerializedName;

public class Data {

  @SerializedName("boolean_truth")
  private boolean boolData;

  @SerializedName("string_data")
  private String text;

  @SerializedName("numeric_data")
  private Integer numericValue;

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
