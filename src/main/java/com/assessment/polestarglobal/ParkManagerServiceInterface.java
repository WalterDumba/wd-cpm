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
    boolean unpark(int ticketNumber);

    /**
     *
     * @return
     */
    boolean compact();

}