package com.sap.bookingplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookingGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleExceptions(Exception ex) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleSeatAlreadyBookedException(SeatAlreadyBookedException ex) {
        return new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    }

    @ExceptionHandler(ShowAlreadyBookedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleShowAlreadyBookedException(ShowAlreadyBookedException ex) {
        return new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", HttpStatus.BAD_REQUEST);

        // Get all errors
        List<String> exceptionalErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, HttpStatus.BAD_REQUEST);
    }
}
