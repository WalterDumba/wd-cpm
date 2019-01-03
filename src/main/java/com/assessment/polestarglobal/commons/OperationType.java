package com.assessment.polestarglobal.commons;

import java.util.stream.Stream;

/**
 * Enum manage the operations allowed on this service
 *
 */
public enum OperationType {

    PARK('p'),
    UNPARK('u'),
    COMPACT('c');

    private final char operationId;

    OperationType(char operationId) {
        this.operationId= operationId;
    }


    public char getOperationId() {
        return operationId;
    }

    public static OperationType fromOperationId(char operationId){
        return Stream
                .of(values())
                .filter( op->op.operationId==operationId)
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(""+operationId));

    }
}
