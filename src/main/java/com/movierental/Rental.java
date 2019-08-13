package com.movierental;

public class Rental {

  private int daysRented;
  private Movie movie;

  public Rental(Movie movie, int daysRented){
    this.movie = movie;
    this.daysRented = daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public int getFrequentRenterPoints() {
    // add frequent renter points
    int frequentRenterPoints = 1;
    // add bonus for a two day new release rental
    if ((movie.getPriceCode() == Movie.NEW_RELEASE)
            &&
            daysRented > 1) frequentRenterPoints++;
    return frequentRenterPoints;
  }

  public double amount() {
    double amount = 0;
    //determine amounts for each line
    switch (movie.getPriceCode()) {
      case Movie.REGULAR:
        amount += 2;
        if (daysRented > 2)
          amount += (daysRented - 2) * 1.5;
        break;
      case Movie.NEW_RELEASE:
        amount += daysRented * 3;
        break;
      case Movie.CHILDRENS:
        amount += 1.5;
        if (daysRented > 3)
          amount += (daysRented - 3) * 1.5;
        break;
    }
    return amount;
  }
}