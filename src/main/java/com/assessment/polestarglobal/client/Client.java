package com.assessment.polestarglobal.client;

import com.assessment.polestarglobal.ParkManagerServiceImpl;
import com.assessment.polestarglobal.ParkManagerServiceInterface;
import com.assessment.polestarglobal.commons.OperationType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Client{



    private ParkManagerServiceInterface parkManagerService;
    private Map<OperationType, Function> operationHandlers;
    private static final Pattern expectePattern= Pattern.compile("TODO:");
    private String parameters;


    public Client(ParkManagerServiceInterface parkManagerService) {
        this.parkManagerService = parkManagerService;
        this.initializeOperationHandlers();
    }




    private void initializeOperationHandlers() {
        this.operationHandlers = new HashMap<>();
        this.registerOperationHandlers();
    }


    private void registerOperationHandlers() {
        this.operationHandlers.put(OperationType.PARK,      (licePlate) ->park((String) licePlate) );
        this.operationHandlers.put(OperationType.UNPARK,    (ticketNumber)->unpark((Integer) ticketNumber));
        this.operationHandlers.put(OperationType.COMPACT,   (discarded)->compact());
    }


    public int park(String licencePlate) {
        return this.parkManagerService.park(licencePlate);
    }

    public boolean unpark(int ticketNumber) {
        return this.parkManagerService.unpark(ticketNumber);
    }

    public boolean compact() {
        return this.parkManagerService.compact();
    }




    private void showResults() {
    }

    private void process() {
        String[] commandArr = parameters.split(",");
    }

    private void collectParameters() {
        this.showMenu();
        Scanner kbd = new Scanner(System.in);
        String input = null;
        do{
            input = kbd.nextLine();
        }while (!validParameters(input));
        this.parameters = input;
    }

    private static boolean validParameters(String input) {
        return true;
        //TODO:
    }

    private void showMenu() {
        System.out.println("|===============================================|");
        System.out.println("|       Park Manager Client App                 |");
        System.out.println("|===============================================|");
        System.out.println("|                                               |");
        System.out.println("|Please introduce your parameters               |");
        System.out.println("|>");
    }


    public static void main(String[] args){

        Client client = new Client(ParkManagerServiceImpl.instance());
        client.collectParameters();
        client.process();
        client.showResults();
    }
}
