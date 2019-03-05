package com.nexaas.vendorapp.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nexaas.vendorapp.VendorappApplicationTests;
import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.infra.InvalidParamException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *  Class to test the iumplementation of VendorDetailRepository.
 */
public class VendorDetailRepositoryTest extends VendorappApplicationTests {

    @Autowired
    private VendorDetailRepository repository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void shoulInsertDataIntoRepository() {
        repository.save(generate());
        Assert.assertTrue(assertCount() >= 1L);
    }

    @Test(expected = InvalidParamException.class)
    public void shouldThrowExceptionNullParam(){
        repository.save(null);
    }

    
    @Test(expected = InvalidParamException.class)
    public void shouldThrowExceptionEmptyParam(){
        repository.save(null);
    }


    /**
     * Helper to count data in vendor detail table.
     * @return the number of items in table.
     */
    private Long assertCount(){
        return jdbc.queryForObject("SELECT COUNT(*) FROM vendor_detail", (rs,row) -> rs.getLong(1));
    }

    /**
     * Helper to generate a list with one vendor detail.
     * @return new list of vendor detail
     */
    private List<VendorDetail> generate(){
        List<VendorDetail> list = new ArrayList<>();
        VendorDetail detail = VendorDetail
            .builder()
            .itemDescription("new item")
            .itemPrice(new BigDecimal(10))
            .merchantAddress("merchantAddress")
            .merchantName("merchantName")
            .purchaseCount(12L)
            .purchaserName("purchaserName")
            .build();
        list.add(detail);
        return list;
    }

}