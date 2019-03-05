package com.nexaas.vendorapp.view.infra;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexaas.vendorapp.infra.InvalidParamException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler to translate runtime exceptions, used by Spring MVC.
 */
@ControllerAdvice
public class Handler {

    /**
     * Create a new ResponseEntity when an exception as throwed.
     * @param ex
     * @param request
     * @param response
     * @return new ResponseEntity with error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof InvalidParamException || ex instanceof IllegalStateException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}