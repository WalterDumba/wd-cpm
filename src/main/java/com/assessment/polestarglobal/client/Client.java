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
    private Map<OperationType, Function<String,?>> operationHandlers;
    private static final Pattern EXPECTED_PATTERN= Pattern.compile("(p\\w{3})*+(u\\d{4})*+(c)*+(,)*");
    private static final String TOKEN=",";
    private static final int CAPACITY=10;
    private String parameters;


    public Client(ParkManagerServiceInterface parkManagerService) {
        this.parkManagerService = parkManagerService;
        this.initializeOperationHandlers();
    }




    private void initializeOperationHandlers() {
        this.operationHandlers = new HashMap<>(CAPACITY);
        this.registerOperationHandlers();
    }


    private void registerOperationHandlers() {
        this.operationHandlers.put(OperationType.PARK, this::park);
        this.operationHandlers.put(OperationType.UNPARK, this::unPark);
        this.operationHandlers.put(OperationType.COMPACT,   (discarded)->compact());
    }


    public int park(String licencePlate) {
        return this.parkManagerService.park(licencePlate);
    }

    public boolean unPark(String ticketNumber) {
        int ticketNr = Integer.valueOf(ticketNumber);
        return this.parkManagerService.unPark(ticketNr);
    }

    public boolean compact() {
        return this.parkManagerService.compact();
    }




    private void showResults() {
        System.out.println(this.parkManagerService.currentState());
    }

    private void process() {
        String[] commandArr = parameters.split(TOKEN);
        char currentOperationId;
        String underlyingParameters;

        for(int i=0; i< commandArr.length; ++i){
            currentOperationId   = commandArr[i].charAt(0);
            underlyingParameters = commandArr[i].length() > 1 ? commandArr[i].substring(1): null;
            Function<String, ?> operationHandler = this.operationHandlers.get(OperationType.fromOperationId(currentOperationId));
            operationHandler.apply(underlyingParameters);
        }
    }

    private void collectParameters() {

        Scanner kbd = new Scanner(System.in);
        String input;
        do{
            this.clearScreen();
            this.showMenu();
            input = kbd.nextLine();
        }while (!validParameters(input));
        this.parameters = input;
    }

    private static void clearScreen() {
        for(int i=0; i<50; ++i)
            System.out.println();
    }


    /**
     * TODO: validation
     *
     * @param input
     * @return
     */
    private static boolean validParameters(String input) {

        //return EXPECTED_PATTERN.matcher(input).find();
        return true;
    }

    private void showMenu() {
        System.out.println("|===============================================|");
        System.out.println("|       Park Manager Client App                 |");
        System.out.println("|===============================================|");
        System.out.println("|                                               |");
        System.out.println("|Please introduce your parameters               |");
        System.out.println("|                                               |");
        System.out.println("|>                                              |");
    }


    public static void main(String[] args){

        Client client = new Client(ParkManagerServiceImpl.instance());
        client.collectParameters();
        client.process();
        client.showResults();
    }
}
