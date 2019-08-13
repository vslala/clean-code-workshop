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
    return movie.getPrice().getFrequentRentalPoints(daysRented);
  }

  public double amount() {
    return movie.getPrice().getCharge(daysRented);
  }

}