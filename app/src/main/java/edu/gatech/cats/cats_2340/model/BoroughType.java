package edu.gatech.cats.cats_2340.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake on 10/10/2017. Its an eum of borough types
 */

public enum BoroughType {

    MANHATTAN("MANHATTAN"),
    STATEN_ISLAND("STATEN ISLAND"),
    QUEENS("QUEENS"),
    BROOKLYN("BROOKLYN"),
    BRONX("BRONX"),
    NONE("None");

    public static List<String> type = Arrays.asList("MANHATTAN", "STATEN ISLAND", "QUEENS",
            "BROOKLYN", "BRONX", "None");

    private final String name;

    BoroughType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Converts a string to a borough type
     * @param s The string
     * @return The string as an enum
     */
    public static BoroughType toBoroughType(String s) {
        for (BoroughType bt : BoroughType.values()) {
            if (bt.toString().equalsIgnoreCase(s)) {
                return bt;
            }
        }
        return BoroughType.NONE;
    }

}
