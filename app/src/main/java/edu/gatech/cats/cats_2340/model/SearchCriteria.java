package edu.gatech.cats.cats_2340.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake on 10/10/2017. This is an object to be passed into SQL
 */

public class SearchCriteria {

    private List<BoroughType> borough;
    private List<LocationType> location;
    private Date startDate;
    private Date endDate;

    /**
     * Constructor
     */
    public SearchCriteria(){

    }

    /**
     * Constructor when Borough is given
     * @param boroughs The boroughs given by a user
     */
    public SearchCriteria(BoroughType... boroughs) {
        borough = Arrays.asList(boroughs);
    }

    /**
     * When location type is given
     * @param locations A SC for location types
     */
    public SearchCriteria(LocationType... locations) {
        location = Arrays.asList(locations);
    }

    /**
     * Search criteria when given a lot
     * @param boroughs The boroughs searched for
     * @param locations The locations searched for
     * @param start The start date
     * @param end The end date
     */
    public SearchCriteria(List<BoroughType> boroughs,
                          List<LocationType> locations, Date start, Date end) {
        if(boroughs == null) {
            borough = null;
        } else {
            borough = new ArrayList<>(boroughs);
        }

        if (locations == null) {
            location = null;
        } else {
            location = new ArrayList<>(locations);
        }
        startDate = start;
        endDate = end;
    }

    /**
     * Getter
     * @return Gets borough list
     */
    public List<BoroughType> getBoroughs() {
        return borough;
    }

    /**
     * Getter
     * @return Get location list
     */
    public List<LocationType> getLocations() {
        return location;
    }

    /**
     * Getter
     * @return Gets start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the search Criteria's end date
     * @return End date
     */
    public Date getEndDate() {
        return endDate;
    }

}
