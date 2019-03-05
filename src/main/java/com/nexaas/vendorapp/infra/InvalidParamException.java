package com.nexaas.vendorapp.infra;

/**
 * Signals that an invalid parameter are present.
 */
public class InvalidParamException extends RuntimeException {

    public InvalidParamException(String message){
        super(message);
    }
}
