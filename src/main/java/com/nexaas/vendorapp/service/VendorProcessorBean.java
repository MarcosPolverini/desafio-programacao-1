package com.nexaas.vendorapp.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.repository.VendorDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Organize all proccess of vendor, import, save and return a total.
 */
@Service
public class VendorProcessorBean implements Serializable {

    @Autowired
    private VendorDetailReader reader;

    @Autowired
    private VendorDetailRepository repository;

    /**
     * Read a String file into a List of {@link VendorDetail} and save in database.
     * @param file with data to proccess into a list of {@link VendorDetail}
     * @return total value
     */
    public BigDecimal processVendorDetail(String file) {
        List<VendorDetail> list = reader.read(file);
        repository.save(list);
        return list.stream()
                .map(v -> v.getItemPrice().multiply(new BigDecimal(v.getPurchaseCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}