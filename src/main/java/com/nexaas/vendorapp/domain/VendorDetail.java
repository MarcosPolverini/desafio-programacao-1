package com.nexaas.vendorapp.domain;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * Domain to represent the vendor_detail table and their columns.
 */
@Data
@Builder
@Setter(AccessLevel.NONE)
public class VendorDetail {

    private String purchaserName;

    private String itemDescription;

    private BigDecimal itemPrice;

    private Long purchaseCount;

    private String merchantAddress;

    private String merchantName;
}