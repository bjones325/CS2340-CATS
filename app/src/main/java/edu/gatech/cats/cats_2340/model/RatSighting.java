package edu.gatech.cats.cats_2340.model;

import java.util.Date;

/**
 * Created by Elijah on 10/10/2017.
 */

public class RatSighting {
    private int _key;
    private Date _created;
    private String _locationType;
    public int _zip;
    public String _address;
    public String _city;
    public String _borough;
    public int _latitude;
    public int _longitude;

    public RatSighting(int key, Date created, String locationType, int zip, String address, String city, String borough, int latitude, int longitude) {
        _key = key;
        _created = created;
        _locationType = locationType;
        _zip = zip;
        _address = address;
        _city = city;
        _borough = borough;
        _latitude = latitude;
        _longitude = longitude;
    }

    public int getKey() {
        return _key;
    }
}
