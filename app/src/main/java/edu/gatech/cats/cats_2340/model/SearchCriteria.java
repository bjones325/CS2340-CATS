package edu.gatech.cats.cats_2340.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake on 10/10/2017.
 */

public class SearchCriteria {

    private List<BuroughType> burough;
    private List<LocationType> location;
    private Date startDate;
    private Date endDate;

    public SearchCriteria(){

    }

    public SearchCriteria(BuroughType... bors) {
        burough = Arrays.asList(bors);
    }

    public SearchCriteria(LocationType... locs) {
        location = Arrays.asList(locs);
    }

    public SearchCriteria(List<BuroughType> buroughs, List<LocationType> locs, Date start, Date end) {
        burough = buroughs;
        location = locs;
        startDate = start;
        endDate = end;
    }

    public List<BuroughType> getBuroughs() {
        return burough;
    }

    public List<LocationType> getLocations() {
        return location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
