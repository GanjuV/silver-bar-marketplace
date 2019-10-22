package com.sbm.dto.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbm.util.DateUtils;

/**
 * Responce class with details.
 * @author HCL
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    /**
     * Get Status.
     * @return String
     */
    public Status getStatus() {
      return status;
    }

    /**
     * Set Status.
     * @param status
     */
    public void setStatus(Status status) {
      this.status = status;
    }

    /**
     * Get payload.
     * @return T
     */
    public T getPayload() {
      return payload;
    }

    /**
     * Set Payload.
     * @param payload
     */
    public void setPayload(T payload) {
      this.payload = payload;
    }

    /**
     * Get Errors.
     * @return Object
     */
    public Object getErrors() {
      return errors;
    }

    /**
     * Set Errors.
     * @param errors
     */
    public void setErrors(Object errors) {
      this.errors = errors;
    }

    /**
     * Get Metadata.
     * @return Object
     */
    public Object getMetadata() {
      return metadata;
    }

    /**
     * Set Metadata.
     * @param metadata
     */
    public void setMetadata(Object metadata) {
      this.metadata = metadata;
    }

    /**
     * Bad Request.
     * @return Response
     */
    public static <T> Response<T> badRequest() {
      Response<T> response = new Response<>();
      response.setStatus(Status.BAD_REQUEST);
      return response;
    }

    /**
     * Status OK.
     * @return Response
     */
    public static <T> Response<T> ok() {
      Response<T> response = new Response<>();
      response.setStatus(Status.OK);
      return response;
    }

    /**
     * Status OK.
     * @return Response
     * @param payload
     */
    public static <T> Response<T> ok(T payload) {
      Response<T> response = new Response<>();
      response.setStatus(Status.OK);
      response.setPayload(payload);
      return response;
    }

    /**
     * Exception.
     * @return Response
     */
    public static <T> Response<T> exception() {
      Response<T> response = new Response<>();
      response.setStatus(Status.EXCEPTION);
      return response;
    }

    /**
     * Not found.
     * @return Response
     */
    public static <T> Response<T> notFound() {
      Response<T> response = new Response<>();
      response.setStatus(Status.NOT_FOUND);
      return response;
    }

    /**
     * Duplicate entity.
     * @return Response
     */
    public static <T> Response<T> duplicateEntity() {
      Response<T> response = new Response<>();
      response.setStatus(Status.DUPLICATE_ENTITY);
      return response;
    }

    /**
     * Add error message to responce.
     * @param  errorMsg
     * @param ex
     */
    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
      ResponseError error = new ResponseError();
      error.setMessage(ex.getMessage());
      error.setTimestamp(DateUtils.today());
      setErrors(error);
    }

    public enum Status {
      OK, BAD_REQUEST, EXCEPTION, NOT_FOUND, DUPLICATE_ENTITY
    }
}

