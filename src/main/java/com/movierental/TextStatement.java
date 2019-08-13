package com.movierental;

class TextStatement {

  public String display(String customerName, Rentals rentals) {
    String result = "Rental Record for " + customerName + "\n";
    for (Rental rental : rentals) {
      result += "\t" + rental.getMovie().getTitle() + "\t" +
          rental.amount() + "\n";
    }
    result += "Amount owed is " + String.valueOf(rentals.totalAmount()) + "\n";
    result += "You earned " + String.valueOf(rentals.totalFrequentRentalPoints())
        + " frequent renter points";
    return result;
  }
}
