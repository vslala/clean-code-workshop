package com.movierental;

abstract class Price {

    abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRentalPoints(int daysRented) {
        return 1;
    }
}


class ChildrenPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }
}

class RegularPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }
}

class NewReleasePrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRentalPoints(int daysRented) {
        return isBonusApplicable(daysRented) ? 2 : 1;
    }

    private boolean isBonusApplicable(int daysRented) {
        return (getPriceCode() == Movie.NEW_RELEASE)
                && daysRented > 1;
    }
}