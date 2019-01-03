package com.assessment.polestarglobal;

public interface ParkManagerServiceInterface {

    /**
     *
     * @param licencePlate
     * @return
     */
    int park(String licencePlate);

    /**
     *
     * @param ticketNumber
     * @return
     */
    boolean unPark(int ticketNumber);

    /**
     *
     * @return
     */
    boolean compact();

    /**
     *
     */
    String currentState();

}