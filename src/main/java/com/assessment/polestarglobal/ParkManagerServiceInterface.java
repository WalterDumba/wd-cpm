package com.assessment.polestarglobal;

public interface ParkManagerServiceInterface {

    /**
     * Park the current car given by the following licencePlate
     *
     * @param licencePlate
     * @return the number of the ticket occupied by the car
     */
    int park(String licencePlate);

    /**
     * Unpark the car on given ticketNumber
     *
     * @param ticketNumber
     * @return true if the car previous parked on the given were successful released,
     *         false the other way out
     */
    boolean unPark(int ticketNumber);

    /**
     * Make the spaces on this park continuos so the parking process becomes efficient
     *
     * @return true if the spaces on this park were successful compacted
     *         false if the other way out
     */
    boolean compact();

    /**
     *Retrieves the current state from the pork on String representation
     */
    String currentState();

}