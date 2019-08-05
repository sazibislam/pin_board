package com.sazib.pinboard.ui.pinboard.view.model;

import androidx.annotation.NonNull;

public class PinboardDataModel {

  private String itemName;
  private String itemDescription;
  private String itemImage;

  public PinboardDataModel(String itemName, String itemDescription, String itemImage) {
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemImage = itemImage;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  public String getItemImage() {
    return itemImage;
  }

  public void setItemImage(String itemImage) {
    this.itemImage = itemImage;
  }

  @NonNull @Override public String toString() {
    return "Pinboard{"
        + "itemName='"
        + itemName
        + '\''
        + "itemDescription='"
        + itemDescription
        + '\''
        + ", itemImage="
        + itemImage
        + '}';
  }
}
