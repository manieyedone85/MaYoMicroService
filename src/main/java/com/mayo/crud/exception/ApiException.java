package com.mayo.crud.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * ExceptionModel class for session expired.
 *
 * @author manigandan subramani
 */
@Data
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields
public class ApiException extends Exception {
    /**
     * Generate unique serial number
     */
    private static final long serialVersionUID = 1L;
    /**
     * Get and Set response HttpStatus
     */
    private final HttpStatus httpStatus;
    /**
     * Get and Set response custom code like 1000,1001,1002 etc.,
     */
    private final Integer code;
    /**
     * Get and Set title
     */
    private final String title;
    /**
     * Get and Set exception
     */
    private final String exception;
    /**
     * Get and Set readable error message for frontend display purpose
     */
    private final String message;
    private final String error;
    /**
     * Get and Set readable error message for frontend display purpose
     */
    private final List<ViolationModel> violations;
}
