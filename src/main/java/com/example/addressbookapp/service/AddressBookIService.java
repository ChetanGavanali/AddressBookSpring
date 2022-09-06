package com.example.addressbookapp.service;

import com.example.addressbookapp.dto.AddressDto;
import com.example.addressbookapp.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookIService {
    AddressBook saveData(AddressBook addressDetail);

    AddressBook saveDatadto(AddressDto addressDetail);

    Optional<AddressBook> FindUserById(Long Id);

    List<AddressBook> findAll();

    AddressBook editUserById(AddressDto dtomodel, Long Id);

    void deleteById(Long Id);

    List<AddressBook> getAddressBookByemail(String email);

    List<AddressBook> getAddressBookBycity(String city);

}