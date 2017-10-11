package edu.gatech.cats.cats_2340.model;

/**
 * Created by Blake on 10/10/2017.
 */

public enum BuroughType {

    MANHATTAN("Manhattan"),
    STATEN_ISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKLYN("Brooklyn"),
    BRONX("Bronx");

    private String name;

    private BuroughType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }
}
