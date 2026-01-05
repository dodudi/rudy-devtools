package kr.it.rudy.server.global.exception;

import kr.it.rudy.server.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.core.exc.UnexpectedEndOfInputException;

import java.util.regex.PatternSyntaxException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred: ", e);
        ApiResponse<Void> response = ApiResponse.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<Void>> handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException occurred: ", e);
        ApiResponse<Void> response = ApiResponse.error("Null value encountered");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation error occurred: ", e);
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failed");
        ApiResponse<Void> response = ApiResponse.error(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Type mismatch error occurred: ", e);
        String errorMessage = String.format("Invalid parameter type for '%s'", e.getName());
        ApiResponse<Void> response = ApiResponse.error(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Method not allowed: ", e);
        ApiResponse<Void> response = ApiResponse.error("HTTP method not supported");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("No handler found: ", e);
        ApiResponse<Void> response = ApiResponse.error("Requested resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PatternSyntaxException.class)
    public ResponseEntity<ApiResponse<Void>> handlePatternSyntaxException(PatternSyntaxException e) {
        log.error("Invalid regular expression: ", e);
        ApiResponse<Void> response = ApiResponse.error("Invalid regular expression");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnexpectedEndOfInputException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedEndOfInputException(UnexpectedEndOfInputException e) {
        log.error("Unexpected end of input: ", e);
        ApiResponse<Void> response = ApiResponse.error("Invalid JSON format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(StreamReadException.class)
    public ResponseEntity<ApiResponse<Void>> handleStreamReadException(StreamReadException e) {
        log.error("Stream read error occurred: ", e);
        ApiResponse<Void> response = ApiResponse.error("Invalid JSON format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected error occurred: ", e);
        ApiResponse<Void> response = ApiResponse.error("Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
