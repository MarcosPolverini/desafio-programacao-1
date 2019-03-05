package com.nexaas.vendorapp.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.infra.InvalidParamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementation of VendorDetailRepository to insert data using Spring
 * JdbcTemplate and pure SQL.
 */
@Repository
class VendorDetailRepositoryJdbcImp implements VendorDetailRepository {

    /** Spring jdbctemplate bean. */
    @Autowired
    private JdbcTemplate jdbc;

    private static final String BATCH_INSERT = "INSERT INTO vendor_detail(purchaser_name,item_description,item_price,"
            + "purchase_count,merchant_address,merchant_name) VALUES(?,?,?,?,?,?)";

    /** {@inheritDoc} */
    @Override
    public void save(List<VendorDetail> details) throws InvalidParamException {
        if(details == null  || (details.isEmpty())){
            throw new InvalidParamException("List of vendor detail is empty");
        }
        jdbc.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                VendorDetail detail = details.get(i);
                ps.setString(1, detail.getPurchaserName());
                ps.setString(2, detail.getItemDescription());
                ps.setBigDecimal(3, detail.getItemPrice());
                ps.setLong(4, detail.getPurchaseCount());
                ps.setString(5, detail.getMerchantAddress());
                ps.setString(6, detail.getMerchantName());
            }

            @Override
            public int getBatchSize() {
                return details.size();
            }
        });
    }
}