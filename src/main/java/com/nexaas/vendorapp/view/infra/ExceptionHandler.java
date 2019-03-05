package com.nexaas.vendorapp.view.infra;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexaas.vendorapp.infra.InvalidFileException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * Component handle with system exceptions : {@link InvalidFileException},
 * {@link InvalidParameterException}, {@link IllegalStateException} <br/>
 * This component use a Spring handler infrasctructure.
 */
@Component
public class ExceptionHandler extends AbstractHandlerExceptionResolver {

    /** {@inheritDoc} */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView mv;
        if (ex instanceof InvalidFileException) {
            mv = new ModelAndView().addObject("Error", ex.getMessage());
            mv.setStatus(HttpStatus.BAD_REQUEST);
            return mv;
        }
        return null;
    }
}