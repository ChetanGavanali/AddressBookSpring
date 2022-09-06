package com.example.addressbookapp.dto;

import com.example.addressbookapp.model.AddressBook;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private String object;

    public ResponseDto(String string, String response) {
        this.message = string;
        this.object = response;
    }

    public ResponseDto(String string, Optional<AddressBook> response) {
        this.message = string;
        this.object = String.valueOf(response);
    }
    public ResponseDto(String string, List<AddressBook> allEmp) {
        this.message = string;
        this.object = allEmp.toString();
    }
}