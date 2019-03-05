package com.nexaas.vendorapp.view;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.io.IOException;
import java.math.BigDecimal;

import com.nexaas.vendorapp.infra.InvalidFileException;
import com.nexaas.vendorapp.service.VendorProcessorBean;
import com.nexaas.vendorapp.view.domain.SummaryTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Rest component to expose vendor RestFull API using Spring MVC.
 */
@RestController
@RequestMapping("/vendor")
@Api(consumes = MULTIPART_FORM_DATA_VALUE, value= "Vendor detail form", description = "Controller for Vendor proccess")
public class VendorController {

    @Autowired
    private VendorProcessorBean bean;

    /**
     * Post method to import and save file of vendor details.
     * @param file to import
     * @throws InvalidFileException when the file is null or when have problems to read the file.
     */
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value= "Add file content data into database")
    @ApiResponse(code = 201, message = "Data imported", response = SummaryTO.class)
    @ResponseBody
    public SummaryTO addVendorList(@RequestParam("file") MultipartFile file) throws InvalidFileException {
        try {
            BigDecimal total = bean.processVendorDetail(new String(file.getBytes()));
            return new SummaryTO(total);
        } catch (IOException e) {
            throw new InvalidFileException();
        }
    }

}