package com.assessment.polestarglobal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParkManagerServiceImpl implements ParkManagerServiceInterface {


    private AtomicInteger tickets;
    private static ParkManagerServiceInterface instance;
    private Map<Integer, String> ticketsAlreadyTaken;
    private static final int TICKET_MONITOR_INITIAL_VALUE = 5000;
    private static final int CAPACITY =10;


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

    @Override
    public int park(String licencePlate) {
        if(this.ticketsAlreadyTaken.size()>CAPACITY){
            return -1;
        }
        int parkId = this.tickets.getAndIncrement();
        this.ticketsAlreadyTaken.put(parkId, licencePlate);
        return parkId;
    }

    @Override
    public boolean unPark(int ticketNumber) {
        String licencePlate = this.ticketsAlreadyTaken.remove(ticketNumber);
        return licencePlate!= null;
    }


    @Override
    public boolean compact() {
        return true;
    }

    @Override
    public String currentState() {
        String[] parkCurrentState = this.ticketsAlreadyTaken.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .toArray( l->new String[10]);
        String parkCurrentStateShortDesc=Stream
                .of(parkCurrentState)
                .reduce( (e1, e2)-> {
                    if(e1 == null) return ",";
                    else if(e2 == null) return e1+",";
                    else return e1+","+e2;
                })
                .get();
        return parkCurrentStateShortDesc;
    }

    @Override
    public String toString() {
        return "ParkManagerServiceImpl{" +
                "ticketsAlreadyTaken=" + ticketsAlreadyTaken +
                '}';
    }
}
