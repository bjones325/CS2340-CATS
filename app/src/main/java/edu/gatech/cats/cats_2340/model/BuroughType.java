package edu.gatech.cats.cats_2340.model;

/**
 * Created by Blake on 10/10/2017.
 */

public enum BuroughType {

    MANHATTAN("MANHATTAN"),
    STATEN_ISLAND("STATEN ISLAND"),
    QUEENS("QUEENS"),
    BROOKLYN("BROOKLYN"),
    BRONX("BRONX"),
    NONE("None");

    private String name;

    private BuroughType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    public static BuroughType toBuroughType(String s) {
        for (BuroughType bt : BuroughType.values()) {
            if (bt.toString().equalsIgnoreCase(s)) {
                return bt;
            }
        }
        return BuroughType.NONE;
    }

}
