package com.example.addressbookapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@Valid
public class AddressDto {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message="Invalid Name")
    String fullName;
    @Pattern(regexp = "^[1-9]{2}[0-9]{10}$", message="Invalid PhoneNumber")
    String phoneNumber;
    String address;
    List<String> city;
    String state;
    @Pattern(regexp = "^[1-9]{1}[0-9]{5}$", message="Invalid Zip Code")
    String zip;
    Long zipcode;
    private  String email;
}
