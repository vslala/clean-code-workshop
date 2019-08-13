package com.movierental;

public class HtmlStatement {

  public String display(Rentals rentals, String customerName) {
    String result = "<h1>Rental Record for <b>" + customerName + "</b></h1><br/>";
    for (Rental rental : rentals) {
      result += rental.getMovie().getTitle() + " " +
              rental.amount() + "<br/>";
    }
    result += "Amount owed is <b>" + String.valueOf(rentals.totalAmount()) + "</b><br/>";
    result += "You earned <b>" + String.valueOf(rentals.totalFrequentRentalPoints())
            + "</b> frequent renter points";
    return result;
  }
}
