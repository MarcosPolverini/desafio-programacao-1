package com.nexaas.vendorapp.infra;

/**
 * Signals that an invalid file are present.
 */
public class InvalidFileException extends Exception {

    /**
     * Default constructor.
     */
    public InvalidFileException() {
        super("The file is invalid");
    }

}