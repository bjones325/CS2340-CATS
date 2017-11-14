package edu.gatech.cats.cats_2340.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Blake on 10/10/2017. This is an object to be passed into SQL
 */

public class SearchCriteria {

    private List<BoroughType> burough;
    private List<LocationType> location;
    private Date startDate;
    private Date endDate;

    /**
     * Contructor
     */
    public SearchCriteria(){

    }

    /**
     * Constructor when Borough is given
     * @param bors The boroughs given by a user
     */
    public SearchCriteria(BoroughType... bors) {
        burough = Arrays.asList(bors);
    }

    /**
     * When location type is given
     * @param locs A SC for location types
     */
    public SearchCriteria(LocationType... locs) {
        location = Arrays.asList(locs);
    }

    /**
     * Search criteria when given a lot
     * @param buroughs The boroughs searched for
     * @param locs The locations searched for
     * @param start The start date
     * @param end The end date
     */
    public SearchCriteria(List<BoroughType> buroughs,
                          List<LocationType> locs, Date start, Date end) {
        burough = new ArrayList<>(buroughs);
        location = new ArrayList<>(locs);
        startDate = start;
        endDate = end;
    }

    /**
     * Getter
     * @return Gets borough list
     */
    public List<BoroughType> getBuroughs() {
        return burough;
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
