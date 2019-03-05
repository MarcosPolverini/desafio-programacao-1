package com.nexaas.vendorapp.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.nexaas.vendorapp.VendorappApplicationTests;
import com.nexaas.vendorapp.domain.VendorDetail;
import com.nexaas.vendorapp.infra.InvalidParamException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to test the implementation of VendorDetailReader.
 */
public class VendorDetailReaderTest extends VendorappApplicationTests {

    @Autowired
    private VendorDetailReader reader;

    public static final String INPUT_FILE = "input.tab";

    public static final String INPUT_FILE_INVALID_LONG = "input_invalid_long.tab";

    public static final String INPUT_FILE_INVALID_BIGDECIMAL = "input_invalid_bigdecimal.tab";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldImportData() {
        List<VendorDetail> result = reader.read(getFile(INPUT_FILE));
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 4);
    }

    @Test
    public void shouldThrowExceptionInvalidLongValueIntoLine3() {
        expectedEx.expect(IllegalStateException.class); 
        expectedEx.expectMessage("Error reading line 3 Purchase Count"); 
        reader.read(getFile(INPUT_FILE_INVALID_LONG));
    }

    @Test
    public void shouldThrowExceptionInvalidBigDecimalValueIntoLine3() {
        expectedEx.expect(IllegalStateException.class); 
        expectedEx.expectMessage("Error reading line 3 Item Price"); 
        reader.read(getFile(INPUT_FILE_INVALID_BIGDECIMAL));
    }

    @Test(expected = InvalidParamException.class)
    public void shoulThrowInvalidParameterExceptionNullFile(){
        reader.read(null);
    }

    
    @Test(expected = InvalidParamException.class)
    public void shoulThrowInvalidParameterExceptionEmptyFile(){
        reader.read(new String());
    }


    /**
     * Read a file into resource and convert to String value using UTF-8 charset.
     * 
     * @param fileName name of file to read.
     * @return String of file test
     */
    public static String getFile(final String fileName) {
        try {
            URI fileUri = ClassLoader.getSystemResource(fileName).toURI();
            byte[] file = Files.readAllBytes(Paths.get(fileUri));
            return new String(file, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error reading test file", e);
        }
    }

}