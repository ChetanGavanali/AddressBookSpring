package com.example.addressbookapp.controller;

import com.example.addressbookapp.dto.AddressDto;
import com.example.addressbookapp.dto.ResponseDto;
import com.example.addressbookapp.model.AddressBook;
import com.example.addressbookapp.service.AddressBookIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {
    @Autowired
    AddressBookIService service;
    @GetMapping("/home")
    public String serviceCall() {
        return ("Welcome to AddressBook");
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseDto> addUserData(@Valid @RequestBody AddressDto addressBookData) {
        AddressBook response = service.saveDatadto(addressBookData);
        ResponseDto responseDTO = new ResponseDto("Data Added Successfully", (Optional.ofNullable(response)));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindUserById(@PathVariable Long Id) {
        Optional<AddressBook> response = service.FindUserById(Id);
        ResponseDto responseDto = new ResponseDto("All Details of Person using Id", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<AddressBook> allUser = service.findAll();
        ResponseDto responseDTO = new ResponseDto("All AddressBook List", allUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    @PutMapping("/edit/{Id}")
    public ResponseEntity<ResponseDto> updateUserById(@PathVariable Long Id, @Valid @RequestBody AddressDto addressBookDTO) {
        Optional<AddressBook> Details = Optional.ofNullable(service.editUserById(addressBookDTO, Id));
        ResponseDto respDTO = new ResponseDto("Person details is updated", Details);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long Id) {
        service.deleteById(Id);
        ResponseDto responseDTO = new ResponseDto("AddressBook Details of person deleted successfully", "Id:" + Id + " is deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getUserByemail(@PathVariable String email) {
        List<AddressBook> personDetailsList = service.getAddressBookByemail(email);
        ResponseDto respDTO = new ResponseDto("Data by using email", personDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseDto> getUserDataByCity(@PathVariable String city) {
        List<AddressBook> PersonDetailsList = service.getAddressBookBycity(city);
        ResponseDto respDTO = new ResponseDto("Data by using city", PersonDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}