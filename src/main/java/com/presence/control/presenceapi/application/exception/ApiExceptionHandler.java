package com.presence.control.presenceapi.application.exception;

import com.presence.control.presenceapi.application.response.ErrorResponse;
import com.presence.control.presenceapi.domain.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, HttpStatus status) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(String.valueOf(status.value()));
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserExistException(UserAlreadyExistsException userAlreadyExistsException){

        Map<String, Object> body = new HashMap<>();

        body.put("message", userAlreadyExistsException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException){

        Map<String, Object> body = new HashMap<>();

        body.put("message", userNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(LocalNotFoundException.class)
    public ResponseEntity<Object> handleLocalNotFoundException(LocalNotFoundException localNotFoundException){

        Map<String, Object> body = new HashMap<>();

        body.put("message", localNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(LocalAlreadyExistsException.class)
    public ResponseEntity<Object> handleLocalAlreadyExistsException(LocalAlreadyExistsException localAlreadyExistsException){

        Map<String, Object> body = new HashMap<>();

        body.put("message", localAlreadyExistsException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DepartmentAlreadyExists.class)
    public ResponseEntity<Object> handleDepartmentAlreadyExists(DepartmentAlreadyExists departmentAlreadyExists){

        Map<String, Object> body = new HashMap<>();

        body.put("message", departmentAlreadyExists.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }


}
