package com.mayo.crud.exception;

/**
 * Custom exception for API validation errors.
 * @author Manigandan.subramani
 */
public class ApiValidationException extends Exception {
    /**
     * Serial version UID used for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param messageIn    - readable error message for frontend display purpose
     */
    public ApiValidationException(final String messageIn) {
        super(messageIn);
    }
}
