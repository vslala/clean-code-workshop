package com.movierental;

import java.util.ArrayList;
import java.util.List;

public class Rentals extends ArrayList<Rental> {

    public double totalAmount() {
        return this.stream().mapToDouble(rental -> rental.amount()).sum();
    }

    public int totalFrequentRentalPoints() {
        return this.stream().mapToInt(rental -> rental.getFrequentRenterPoints()).sum();
    }
}
