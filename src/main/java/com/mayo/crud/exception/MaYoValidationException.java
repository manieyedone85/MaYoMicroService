package com.mayo.crud.exception;

import java.util.List;

/**
 * Custom Violation exception for API validation errors.
 *
 * @author Manigandan.subramani
 */
public class MaYoValidationException extends Exception {
    /**
     * Generate unique serial number
     */
    private static final long serialVersionUID = 1L;

    /**
     * Get and Set list validation error messages
     */
    private final List<ViolationModel> violationModels;

    /**
     * @param messageIn    - readable error message for frontend display purpose
     * @param violationsIn - readable list of validation error details for identify the exception
     */
    public MaYoValidationException(final String messageIn, final List<ViolationModel> violationsIn) {
        super(messageIn);
        this.violationModels = violationsIn;
    }

    /**
     * @return Violation - list of validation error details
     */
    public List<ViolationModel> getViolations() {
        return violationModels;
    }
}
