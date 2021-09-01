package com.example.demo.core.exceptionHandlers;
/*
package com.example.demo.core.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.core.exceptions.BadRequestException;
import com.example.demo.core.exceptions.InternalServerErrorException;
import com.example.demo.core.exceptions.NotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class GlobalHandlerException extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler, 
      Exception ex) {
          
        try {
            if (ex instanceof BadRequestException) {
                return handleBadRequest((BadRequestException) ex, response);
            }
            else if(ex instanceof NotFoundException){
                return handleNotFound((NotFoundException) ex, response);
            }
            else if(ex instanceof InternalServerErrorException){
                return handleInternalServerError((InternalServerErrorException) ex, response);
            }
        } catch(IOException ioException){
            System.out.println("IOException");

        } catch (Exception handlerException) {
            System.out.println("Hello");

        }
        return null;
    }

    private ModelAndView handleBadRequest(BadRequestException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    private ModelAndView handleNotFound(NotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }

    private ModelAndView handleInternalServerError(InternalServerErrorException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }
}
*/