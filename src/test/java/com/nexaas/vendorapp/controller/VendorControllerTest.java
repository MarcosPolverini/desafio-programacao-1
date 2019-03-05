package com.nexaas.vendorapp.controller;

import static com.nexaas.vendorapp.service.VendorDetailReaderTest.INPUT_FILE;
import static com.nexaas.vendorapp.service.VendorDetailReaderTest.INPUT_FILE_INVALID_BIGDECIMAL;
import static com.nexaas.vendorapp.service.VendorDetailReaderTest.INPUT_FILE_INVALID_LONG;
import static com.nexaas.vendorapp.service.VendorDetailReaderTest.getFile;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nexaas.vendorapp.VendorappApplicationTests;
import com.nexaas.vendorapp.view.VendorController;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Class to test the {@link VendorController} API.
 */
public class VendorControllerTest extends VendorappApplicationTests {

    @Test
    public void shouldUpoloadAFile() throws Exception {
        execute(INPUT_FILE, status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnError400BigDecimalInvalid() throws Exception {
        execute(INPUT_FILE_INVALID_BIGDECIMAL, status().is4xxClientError());
    }

    @Test
    public void shouldReturnError400LongInvalid() throws Exception {
        execute(INPUT_FILE_INVALID_LONG, status().is4xxClientError());
    }

    private void execute(String fileName, ResultMatcher resultMatcher) throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", fileName, MULTIPART_FORM_DATA_VALUE,getFile(fileName).getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/vendor").file(mockFile)).andExpect(resultMatcher);
    }

}