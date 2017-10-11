package edu.gatech.cats.cats_2340.model;

import java.util.Date;

/**
 * Created by Elijah on 10/10/2017.
 */

public class RatSighting {
    private int _key;
    private int _created;
    private LocationType _locationType;
    public int _zip;
    public String _address;
    public String _city;
    public BuroughType _borough;
    public int _latitude;
    public int _longitude;

    public RatSighting(int key, int created, LocationType locationType, int zip, String address, String city, BuroughType borough, int latitude, int longitude) {
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

    public int getCreated() {
        return _created;
    }

    public LocationType getLocationType() {
        return _locationType;
    }

    public int getZip() {
        return _zip;
    }

    public String getAddr() {
        return _address;
    }
    public String getCity() {
        return _city;
    }
    public BuroughType getBorough() {
        return _borough;
    }
    public int getLat() {
        return _key;
    }
    public int getLong() {
        return _key;
    }


}
