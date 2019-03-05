package com.nexaas.vendorapp.view.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Summary entity to represent a json with total value.
 */
@AllArgsConstructor
@Data
public class SummaryTO {

    private BigDecimal total;

}