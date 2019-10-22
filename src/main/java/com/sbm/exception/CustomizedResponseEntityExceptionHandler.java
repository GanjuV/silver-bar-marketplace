package com.sbm.exception;

import com.sbm.dto.model.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customized response entity exception handler.
 * @author HCL
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handling entity not found exception.
   *
   * @param ex, request
   * @return ResponseEntity
   */
    @ExceptionHandler(SBMException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFoundExceptions(Exception ex, WebRequest request) {
      Response response = Response.notFound();
      response.addErrorMsgToResponse(ex.getMessage(), ex);
      return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

  /**
   * Handling duplicate entity.
   *
   * @param ex, request
   * @return ResponseEntity
   */
    @ExceptionHandler(SBMException.DuplicateEntityException.class)
    public final ResponseEntity handleDuplicateEntityExceptions(Exception ex, WebRequest request) {
      Response response = Response.duplicateEntity();
      response.addErrorMsgToResponse(ex.getMessage(), ex);
      return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

  /**
   * Overriding validation error.
   *
   * @param ex, headers, status, request
   * @return ResponseEntity
   */
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      Response response = Response.exception();
      response.addErrorMsgToResponse(ex.getMessage(), ex);
      return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
