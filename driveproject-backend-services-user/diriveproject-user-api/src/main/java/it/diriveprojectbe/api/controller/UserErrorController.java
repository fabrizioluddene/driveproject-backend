package it.diriveprojectbe.api.controller;

import it.diriveprojectbe.commons.message.ApplicationCodeEnum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class UserErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto> exceptionHandler(Exception ex) {
        GenericResponseDto responseDto = new GenericResponseDto();

        if (ex instanceof DataIntegrityViolationException) {
            responseDto.setCode(ApplicationCodeEnum.UNIQUECONSTRAINT);
            responseDto.setDescription("User already present");
            return new ResponseEntity<GenericResponseDto>(responseDto, HttpStatus.CONFLICT);
        }else if (ex instanceof HttpMessageNotReadableException){
            responseDto.setCode(ApplicationCodeEnum.FIELD);
            responseDto.setDescription(ex.getMessage());
            return new ResponseEntity<GenericResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
        }else{
            responseDto.setCode(ApplicationCodeEnum.APPLICATIONERROR);
            responseDto.setDescription(ex.getMessage());
            return new ResponseEntity<GenericResponseDto>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<GenericResponseDto>>  handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<GenericResponseDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            GenericResponseDto responseDto = new GenericResponseDto();
            responseDto.setCode(ApplicationCodeEnum.FIELD);
            responseDto.setDescription(errorMessage);
            errors.add(responseDto);
        });
        return new ResponseEntity<List<GenericResponseDto>>(errors, HttpStatus.BAD_REQUEST);
    }
}
