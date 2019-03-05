package com.nexaas.vendorapp.service;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.List;

import com.nexaas.vendorapp.domain.VendorDetail;
/**
 * Interface to read a string text to {@link VendorDetail}.
 */
public interface VendorDetailReader extends Serializable{

    /**
     * Read the file string into a list of {@link VendorDetail}.
     * @param file with data to read.
     * @throws InvalidParameterException when the file is empty or null.
     * @throws IllegalStateException when the file is not in default format or contains invalid value.
     */
    List<VendorDetail> read(String file) throws InvalidParameterException, IllegalStateException;

}