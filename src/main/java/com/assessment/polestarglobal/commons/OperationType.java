package com.assessment.polestarglobal.commons;

import java.util.stream.Stream;

public enum OperationType {

    PARK("p"),
    UNPARK("u"),
    COMPACT("c");

    private final String operationId;

    OperationType(String opeartionId) {
        this.operationId= opeartionId;
    }


    public static OperationType fromOperationId(String operationId){
        return Stream
                .of(values())
                .filter( op->operationId.equals(operationId))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(operationId));

    }
}
