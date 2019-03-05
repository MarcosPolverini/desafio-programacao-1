package com.nexaas.vendorapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.infra.InvalidParamException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Implementation of {@link VendorDetailReader} using de positional sequency to
 * transform and read the data.
 */
@Service
class PositionalVendorDetailReaderImp implements VendorDetailReader {

    private static final Logger LOG = LogManager.getLogger(PositionalVendorDetailReaderImp.class);

    /** {@inheritDoc} */
    @Override
    public List<VendorDetail> read(final String file) throws InvalidParamException, IllegalStateException {
        if (StringUtils.isEmpty(file)) {
            throw new InvalidParamException("Empty file");
        }
        final AtomicInteger line = new AtomicInteger();
        try {
            return Stream.of(file.split(System.getProperty("line.separator")))
                    // Ignore first line
                    .skip(1)
                    // Map to object
                    .map(s -> transform(s.split("\t")))
                    // line incrementor
                    .peek(s -> line.getAndIncrement()).collect(Collectors.toList());
        } catch (InvalidParamException e) {
            LOG.error(e);
            String errorMessage = String.format("Error reading line %s %s", line.get() + 1, e.getMessage());
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * Helper to transform the string array into a {@link VendorDetail} object.
     * @param fields containing 6 positions to convert
     * @return a filledup object {@link VendorDetail}
     */
    private VendorDetail transform(final String[] fields) {
        return VendorDetail.builder().purchaserName(fields[0]).itemDescription(fields[1])
                .itemPrice(toBigDecimal(fields[2], "Item Price"))
                .purchaseCount(toLongValue(fields[3], "Purchase Count")).merchantAddress(fields[4])
                .merchantName(fields[5]).build();
    }

    /**
     * Helper to transform the string value into a {@link Long} object.
     * @param value to convert
     * @param errorFlag used when value cannot converted to {@link Long}
     * @return Long representation of String value.
     * @throws InvalidParamException when the value as invalid  
     */
    private Long toLongValue(final String value, final String errorFlag) throws InvalidParamException {
        try {
            // Removing spaces
            String longValue = value.replaceAll("/s", "");
            return Long.valueOf(longValue);
        } catch (NumberFormatException e) {
            throw new InvalidParamException(errorFlag);
        }
    }

    /**
     * Helper to transform the string value into a {@link BigDecimal} object.
     * @param value to convert
     * @param errorFlag used when value cannot converted to {@link BigDecimal}
     * @return BigDecimal representation of String value.
     * @throws InvalidParamException when the value as invalid  
     */
    private BigDecimal toBigDecimal(final String value, final String errorFlag) throws InvalidParamException {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new InvalidParamException(errorFlag);
        }
    }
}