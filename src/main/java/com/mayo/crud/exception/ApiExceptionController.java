package com.mayo.crud.exception;

import org.apache.hc.client5.http.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling exception globally.
 *
 * @author Manigandan.subramani
 */
@ControllerAdvice
public class ApiExceptionController {

    /**
     * LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionController.class);

    /**
     * @param exception exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ExceptionModel> handleApiBotreeException(final ApiException exception) {
        LOG.error("Got API exception with code={}, message={}", exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(exception.getHttpStatus().value())
                .title("Warning!")
                .message(exception.getMessage())
                .httpStatus(exception.getHttpStatus())
                .violations(exception.getViolations())
                .build(), exception.getHttpStatus());
    }

    /**
     * @param exception botree custom validation exception handling
     * @return ResponseEntity
     */
    @ExceptionHandler(value = MaYoValidationException.class)
    public ResponseEntity<ExceptionModel> handleBotreeValidationException(final MaYoValidationException exception) {
        LOG.error("Got API request validation exception with code={}, message={}", HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .title("Warning!")
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .violations(exception.getViolations())
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = ApiValidationException.class)
    public ResponseEntity<ExceptionModel> handleApiBotreeException(final ApiValidationException exception) {
        LOG.error("Got API exception with message={}", exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .title("Warning!")
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error("Invalid data")
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception - Globally exception capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handleExceptions(final Exception exception) {
        LOG.error("Got API exception with code={}, message={}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Server Issue")
                .message("Something went wrong, contact your admin.")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(exception.toString())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param exception - Globally HttpHostConnectException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(HttpHostConnectException.class)
    public ResponseEntity<ExceptionModel> handleHttpHostConnectException(final HttpHostConnectException exception) {
        LOG.error("Got API exception with code={}, message={}", HttpStatus.BAD_GATEWAY.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_GATEWAY.value())
                .title("Server Issue")
                .message("Unable to connect to the child service.")
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .error(exception.toString())
                .build(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * @param exception - Globally RestClientException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ExceptionModel> handleRestClientException(final RestClientException exception) {
        LOG.error("Got API exception with code={}, message={}", HttpStatus.BAD_GATEWAY.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_GATEWAY.value())
                .title("Server Issue")
                .message("Unable to connect to the child service.")
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .error(exception.toString())
                .build(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * @param exception - Globally Method Argument NotValid exception capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> handleMethodArgumentNotValidExceptions(final MethodArgumentNotValidException exception) {
        LOG.error("Got Method Argument NotValid exception with code={}, message={}", HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
        List<ViolationModel> error = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            error.add(new ViolationModel(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_GATEWAY.value())
                .title("Invalid Request")
                .message("Please give all required fields.")
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .violations(error)
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception - Globally Method Argument Type Mismatch exception capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionModel> handleMethArgTypeMismatchExc(final MethodArgumentTypeMismatchException exception) {
        LOG.error("Got Method Argument Type Mismatch exception with code={}, message={}",
                HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        List<ViolationModel> error = new ArrayList<>();
        error.add(new ViolationModel(exception.getName(), String.format("Invalid value for parameter '%s': %s",
                exception.getName(), exception.getValue())));
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_GATEWAY.value())
                .title("Invalid Request")
                .message("Please give valid input.")
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .violations(error)
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception - Globally Constraint Violation exception capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionModel> handleConstraintViolationExc(final ConstraintViolationException exception) {
        LOG.error("Got Constraint Violation exception with code={}, message={}", HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());
        List<ViolationModel> error = new ArrayList<>();
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            error.add(new ViolationModel(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.BAD_GATEWAY.value())
                .title("Invalid Request")
                .message("Please give all required fields.")
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .violations(error)
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception - Globally Constraint Violation exception capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionModel> handleMediaTypeNotSupException(final HttpMediaTypeNotSupportedException exception) {
        LOG.error("Got HttpMediaTypeNotSupportedException with code={}, message={}",
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                exception.getMessage());

        StringBuilder builder = new StringBuilder();
        builder.append(exception.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        exception.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        List<ViolationModel> error = new ArrayList<>();
        error.add(new ViolationModel("files", builder.toString()));

        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .title("Invalid Request")
                .message("Unsupported media type.")
                .httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .violations(error)
                .error(exception.toString())
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * @param exception - Globally HttpRequestMethodNotSupportedException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionModel> handleRequestMethodNotSupExc(final HttpRequestMethodNotSupportedException exception) {
        LOG.error("Got HttpRequestMethodNotSupportedException with code={}, message={}",
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .title("Invalid Request")
                .message("Method not supported.")
                .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
                .error(String.format("Method not supported. Supported methods are: %s",
                        exception.getSupportedHttpMethods()))
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * @param exception - Globally NoSuchMethodException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<ExceptionModel> handleNoSuchMethodException(final NoSuchMethodException exception) {
        LOG.error("Got NoSuchMethodException with code={}, message={}", HttpStatus.NOT_FOUND.value(),
                exception.getMessage());

        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .title("Invalid Request")
                .message("Method not found: " + exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .error(exception.toString())
                .build(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param exception - Globally DataAccessException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ExceptionModel> handleDataAccessException(final DataAccessException exception) {

        LOG.error("Got API exception with code={}, message={}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Server Issue")
                .message("Unable to connect to the database server instance.")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(exception.toString())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param exception - Globally SQLException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionModel> handleSQLException(final SQLException exception) {

        LOG.error("Got API exception with code={}, message={}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Server Issue")
                .message("Unable to fetch data from the database due to bad sql.")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(exception.toString())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param exception - Globally BadSqlGrammarException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ExceptionModel> handleBadSqlGrammarException(final BadSqlGrammarException exception) {

        LOG.error("Got API exception with code={}, message={}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Server Issue")
                .message("Unable to fetch data from the database due to bad sql.")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(exception.toString())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param exception - Globally FileNotFoundException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionModel> handleFileNotFoundException(final FileNotFoundException exception) {
        LOG.error("Got API exception with code={}, message={}", HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .title("Server Issue")
                .message("File not found.")
                .httpStatus(HttpStatus.NOT_FOUND)
                .error(exception.toString())
                .build(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param exception - Globally NoSuchFileException capture
     * @return ResponseEntity - understandable error response
     */
    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ExceptionModel> handleNoSuchFileException(final NoSuchFileException exception) {
        LOG.error("Got API exception with code={}, message={}", HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
        return new ResponseEntity<>(ExceptionModel.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .title("Server Issue")
                .message("File not found.")
                .httpStatus(HttpStatus.NOT_FOUND)
                .error(exception.toString())
                .build(), HttpStatus.NOT_FOUND);
    }
}
