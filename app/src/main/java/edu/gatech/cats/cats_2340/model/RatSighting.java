package edu.gatech.cats.cats_2340.model;

import android.util.Log;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Elijah on 10/10/2017.
 */

public class RatSighting implements Serializable {
    private int _key;
    private Date _created;
    private LocationType _locationType;
    private int _zip;
    private String _address;
    private String _city;
    private BuroughType _borough;
    private double _latitude;
    private double _longitude;

    /**
     * Constructor for an instance for a RatSighting
     * @param key
     * @param created
     * @param locationType
     * @param zip
     * @param address
     * @param city
     * @param borough
     * @param latitude
     * @param longitude
     */
    public RatSighting(int key, Date created, LocationType locationType, int zip, String address, String city, BuroughType borough, float latitude, float longitude) {
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

    /**
     * no-args constructor for a RatSighting
     */
    public RatSighting() {
        this(0, new Date(0,0,0), LocationType.OTHER, 0, "null", "null", BuroughType.NONE, 0, 0);
    }
    /**
     * getter for key for a RatSighting
     * @return key
     */
    public int getKey() {
        return _key;
    }

    /**
     * getter for created for a RatSighting
     * @return created
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * getter for locationType for a RatSighting
     * @return locationType
     */
    public LocationType getLocationType() {
        return _locationType;
    }

    /**
     * getter for zip for a RatSighting
     * @return zip
     */
    public int getZip() {
        return _zip;
    }

    /**
     * getter for address for a RatSighting
     * @return address
     */
    public String getAddr() {
        return _address;
    }

    /**
     * getter for city for a RatSighting
     * @return city
     */
    public String getCity() {
        return _city;
    }

    /**
     * getter for borough for a RatSighting
     * @return borough
     */
    public BuroughType getBorough() {
        return _borough;
    }

    /**
     * getter for latitude for a RatSighting
     * @return latitude
     */
    public double getLat() {
        return _latitude;
    }

    /**
     * getter for longitude for a RatSighting
     * @return longitude
     */
    public double getLong() {
        return _longitude;
    }

    /**
     * toString method for RatSighting
     * @return string that holds information about the locationType and borough
     */
    public String toString() {
        return _locationType.toString() + " " + _borough.toString();
    }


    /**
     * setter for key for a RatSighting
     */
    public void setKey(int key) {
        this._key = key;
    }

    /**
     * setter for created for a RatSighting
     */
    public void setCreated(String created) {
        this._created = Date.valueOf(created);
    }

    /**
     * setter for locationType for a RatSighting
     */
    public void setLocationType(LocationType locationType) {
        this._locationType = locationType;
    }

    /**
     * setter for zip for a RatSighting
     */
    public void setZip(int zip) {
        this._zip = zip;
    }

    /**
     * setter for address for a RatSighting
     */
    public void setAddress(String address) {
        this._address = address;
    }

    /**
     * setter for city for a RatSighting
     */
    public void setCity(String city) {
        this._city = city;
    }

    /**
     * setter for borough for a RatSighting
     */
    public void setBorough(BuroughType borough) {
        this._borough = borough;
    }

    /**
     * setter for latitude for a RatSighting
     */
    public void setLatitude(float _latitude) {
        this._latitude = _latitude;
    }

    /**
     * setter for longitude for a RatSighting
     */
    public void setLongitude(float _longitude) {
        this._longitude = _longitude;
    }

    public String formatDateString(String created) {
        String[] split = created.split("/");
        String year = split[2].substring(0, 4);
        String result = year + "-" + split[0] + "-" + split[1];
        return result;
    }
}
