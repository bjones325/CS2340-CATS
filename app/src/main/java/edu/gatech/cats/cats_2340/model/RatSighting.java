package edu.gatech.cats.cats_2340.model;

import java.util.Date;

/**
 * Created by Elijah on 10/10/2017.
 */

public class RatSighting {
    private int _key;
    private String _created;
    private LocationType _locationType;
    private int _zip;
    private String _address;
    private String _city;
    private BuroughType _borough;
    private float _latitude;
    private float _longitude;

    public RatSighting(int key, String created, LocationType locationType, int zip, String address, String city, BuroughType borough, float latitude, float longitude) {
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

    public RatSighting() {

    }
    public int getKey() {
        return _key;
    }
    public String getCreated() {
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
    public float getLat() {
        return _latitude;
    }
    public float getLong() {
        return _longitude;
    }

    public String toString() {
        return _locationType.toString() + " " + _borough.toString();
    }
    public void setKey(int key) {
        this._key = key;
    }

    public void setCreated(String created) {
        this._created = created;
    }

    public void setLocationType(LocationType locationType) {
        this._locationType = locationType;
    }

    public void setZip(int zip) {
        this._zip = zip;
    }

    public void setAddress(String address) {
        this._address = address;
    }

    public void setCity(String city) {
        this._city = city;
    }

    public void setBorough(BuroughType borough) {
        this._borough = borough;
    }

    public void setLatitude(float _latitude) {
        this._latitude = _latitude;
    }

    public void setLongitude(float _longitude) {
        this._longitude = _longitude;
    }
}
