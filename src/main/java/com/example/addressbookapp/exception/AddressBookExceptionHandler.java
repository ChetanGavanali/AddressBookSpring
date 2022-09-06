package com.example.addressbookapp.exception;

import com.example.addressbookapp.dto.AddressDto;
import com.example.addressbookapp.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AddressDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errmsg = errorList.stream()
                .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDto respDTO = new ResponseDto("Exception while processing rest request", errmsg.toString());
        return new ResponseEntity(respDTO, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(AddressBookException exception){
        ResponseDto respDTO = new ResponseDto("Exception while processing REST request",
                exception.getMessage());
        return new ResponseEntity(respDTO, HttpStatus.BAD_REQUEST);
    }

}