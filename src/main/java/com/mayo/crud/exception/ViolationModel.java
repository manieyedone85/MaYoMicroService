package com.mayo.crud.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a violation in the system.
 * This class is used to track and handle violations.
 *
 * @author Manigandan.subramani
 */
@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields
public class ViolationModel {
    /**
     * Get and Set field name of the exception.
     */
    private final String fieldName;
    /**
     * Get and Set readable exception message.
     */
    private final String message;
}
