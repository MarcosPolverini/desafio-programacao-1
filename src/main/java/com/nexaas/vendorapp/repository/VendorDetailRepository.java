package com.nexaas.vendorapp.repository;

import java.io.Serializable;
import java.util.List;

import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.infra.InvalidParamException;

/**
 * Contract of VendorDetail Repository Bean.
 */
public interface VendorDetailRepository extends Serializable {

    /**
     * Save a lista of objects into database.
     * The list can't be null.
     * @param details list of VendorDetail
     * @throws InvalidParamException if the parameter is null or empty.
     */
    void save(List<VendorDetail> details) throws InvalidParamException;
}