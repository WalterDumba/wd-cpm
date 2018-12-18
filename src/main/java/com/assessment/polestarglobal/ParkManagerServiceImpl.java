package com.assessment.polestarglobal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkManagerServiceImpl implements ParkManagerServiceInterface {


    private AtomicInteger tickets;
    private static ParkManagerServiceInterface instance;
    private static final int TICKET_MONITOR_INITIAL_VALUE = 5000;
    private Map<Integer, String> ticketsAlreadyTaken;



    /**
     * Ensure we get the singleton from this class
     *
     * Using Double Checked lock idiom
     *
     * @return
     */
    public static ParkManagerServiceInterface instance(){

        if(instance == null){
            synchronized (ParkManagerServiceImpl.class){
                if(instance == null){
                    instance = new ParkManagerServiceImpl(TICKET_MONITOR_INITIAL_VALUE);
                }
            }
        }
        return instance;
    }


    private ParkManagerServiceImpl(int ticketMonitorInitialValue){
        this.tickets                = new AtomicInteger(ticketMonitorInitialValue);
        this.ticketsAlreadyTaken    = new ConcurrentHashMap<>();
    }

    public int park(String licencePlate) {
        int parkId = this.tickets.getAndIncrement();
        this.ticketsAlreadyTaken.put(parkId, licencePlate);
        return parkId;
    }

    public boolean unpark(int ticketNumber) {
        String licencePlate = this.ticketsAlreadyTaken.remove(ticketNumber);
        return licencePlate!= null;
    }


    public boolean compact() {
        return true;
    }
}
