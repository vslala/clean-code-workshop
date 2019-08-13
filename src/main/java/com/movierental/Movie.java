package com.movierental;

public class Movie {
  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;

  private String title;
  private Price price;

  public Movie(String title, int price) {
    this.title = title;
    setPrice(price);
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(int priceCode) {
    switch (priceCode) {
      case Movie.REGULAR:
        price = new RegularPrice();
        break;
      case Movie.CHILDRENS:
        price = new ChildrenPrice();
        break;
      case Movie.NEW_RELEASE:
        price = new NewReleasePrice();
        break;
      default:
        throw new IllegalArgumentException("Price code is not valid.");
    }
  }

  public String getTitle() {
    return title;
  }

}