package com.sbm.dto.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseError {

    private Date timestamp;
    private String message;

    public Date getTimestamp() {
      return timestamp;
    }

    public String getMessage() {
      return message;
    }


    public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
    }

    public void setMessage(String message) {
      this.message = message;
    }

}
