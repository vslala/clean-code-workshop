package com.movierental;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomerTest {

    private Customer customer;

    final String CUSTOMER_NAME = "Varun Shrivastava";

    String correctStatement(String name, List<Movie> movies, int daysRented) {
        double totalAmount = 0;
        StringBuilder sb = new StringBuilder();
        int frequentRenterPoints = 0;
        if (! Objects.isNull(movies)) {
            for (Movie movie : movies) {
                double amount = 0;
                switch (movie.getPriceCode()) {
                    case Movie.REGULAR:
                        amount += 2;
                        if (daysRented > 2) {
                            amount += (daysRented - 2) * 1.5;
                        }
                        break;
                    case Movie.NEW_RELEASE:
                        amount += daysRented * 3;
                        break;
                    case Movie.CHILDRENS:
                        amount += 1.5;
                        if (daysRented > 3) {
                            amount += (daysRented - 3) * 1.5;
                        }
                        break;
                }

                if (! Objects.isNull(movie)) {
                    frequentRenterPoints ++;
                    if ((movie.getPriceCode() == Movie.NEW_RELEASE)
                            &&
                            daysRented > 1) frequentRenterPoints++;
                }

                String movieText = Objects.isNull(movie) ? "" :"\t"+ movie.getTitle() +"\t"+ amount + "\n" ;
                sb.append(movieText);
                totalAmount += amount;
            }
        }




        final String correctOutput = "Rental Record for " + name + "\n" +
                sb +
                "Amount owed is "+ totalAmount +"\n" +
                "You earned "+ frequentRenterPoints +" frequent renter points";
        return correctOutput;
    }

    @Before
    public void setup() {
        customer = new Customer("Varun Shrivastava");
    }

    @Test
    public void customerHasNotRentedAnyMovie(){
        Movie newRelease = new Movie("Kabir Singh", Movie.NEW_RELEASE);
        Movie regular = new Movie("DDLJ", Movie.REGULAR);
        Movie children = new Movie("Bob The Builder", Movie.CHILDRENS);

        final int daysRented = 3;
        Rental newReleaseMovie = new Rental(newRelease, daysRented);
        Rental regularMovie = new Rental(regular, daysRented);
        Rental childrenMovie = new Rental(children, daysRented);

        customer.addRental(newReleaseMovie);
        customer.addRental(regularMovie);
        customer.addRental(childrenMovie);

        String statement = customer.statement();
        Assert.assertEquals(correctStatement(CUSTOMER_NAME, Arrays.asList(new Movie[]{newRelease, regular, children}), 3), statement);
        System.out.println(statement);
    }

    @Test
    public void customerRentedTheRegularMovie() {
        Movie rentedMovie = new Movie("DDLJ", Movie.REGULAR);
        final int daysRented = 3;
        Rental movie = new Rental(rentedMovie, daysRented);
        customer.addRental(movie);
        String statement = customer.statement();
        Assert.assertEquals(correctStatement(CUSTOMER_NAME, Arrays.asList(rentedMovie), daysRented), statement);
    }

    @Test
    public void customerRentsTheChildrenMovie() {
        Movie rentedMovie = new Movie("Bob The Builder", Movie.CHILDRENS);
        final int daysRented = 23;
        Rental movie = new Rental(rentedMovie, daysRented);
        customer.addRental(movie);
        String statement = customer.statement();
        Assert.assertEquals(correctStatement(CUSTOMER_NAME, Arrays.asList(rentedMovie), daysRented), statement);
    }

    @Test
    public void customerRentsTheNewRelease() {
        Movie newRelease = new Movie("Kabir Singh", Movie.NEW_RELEASE);
        Movie regular = new Movie("DDLJ", Movie.REGULAR);
        Movie children = new Movie("Bob The Builder", Movie.CHILDRENS);

        final int daysRented = 3;
        Rental newReleaseMovie = new Rental(newRelease, daysRented);
        Rental regularMovie = new Rental(regular, daysRented);
        Rental childrenMovie = new Rental(children, daysRented);

        customer.addRental(newReleaseMovie);
        customer.addRental(regularMovie);
        customer.addRental(childrenMovie);

        String statement = customer.statement();
        System.out.println(statement);
        String generated = correctStatement(CUSTOMER_NAME, Arrays.asList(new Movie[]{newRelease, regular, children}), daysRented);
        System.out.println(generated);

        Assert.assertEquals(correctStatement(CUSTOMER_NAME, Arrays.asList(new Movie[]{newRelease, regular, children}), daysRented), statement);
    }

    @Test
    public void customerRentedTheNewReleaseForOneDay() {
        Movie rentedMovie = new Movie("Kabir Singh", Movie.NEW_RELEASE);
        final int daysRented = 1;
        Rental movie = new Rental(rentedMovie, daysRented);
        customer.addRental(movie);
        String statement = customer.statement();
        Assert.assertEquals(correctStatement(CUSTOMER_NAME, Arrays.asList(rentedMovie), daysRented), statement);
    }

    @Test
    public void itShouldGenerateHtmlStatement() {
        Movie newRelease = new Movie("Kabir Singh", Movie.NEW_RELEASE);
        Movie regular = new Movie("DDLJ", Movie.REGULAR);
        Movie children = new Movie("Bob The Builder", Movie.CHILDRENS);

        final int daysRented = 3;
        Rental newReleaseMovie = new Rental(newRelease, daysRented);
        Rental regularMovie = new Rental(regular, daysRented);
        Rental childrenMovie = new Rental(children, daysRented);

        customer.addRental(newReleaseMovie);
        customer.addRental(regularMovie);
        customer.addRental(childrenMovie);

        String statement = customer.htmlStatement();
        System.out.println(statement);
        String generated = correctStatement(CUSTOMER_NAME, Arrays.asList(new Movie[]{newRelease, regular, children}), daysRented);
        System.out.println(generated);

        final String expectedOutput = "<h1>Rental Record for <b>Varun Shrivastava</b></h1><br/>" +
                "Kabir Singh 9.0<br/>" +
                "DDLJ 3.5<br/>" +
                "Bob The Builder 1.5<br/>" +
                "Amount owed is <b>14.0</b><br/>" +
                "You earned <b>4</b> frequent renter points";
        Assert.assertEquals(expectedOutput, statement);
    }



}