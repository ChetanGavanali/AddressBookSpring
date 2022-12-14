package com.example.addressbookapp.service;

import com.example.addressbookapp.dto.AddressDto;
import com.example.addressbookapp.exception.AddressBookException;
import com.example.addressbookapp.model.AddressBook;
import com.example.addressbookapp.repo.Repo;
import com.example.addressbookapp.util.EmailSenderServices;
import com.example.addressbookapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressBookService implements AddressBookIService {
    @Autowired
    Repo repository;

    @Autowired
    EmailSenderServices sendEmail;
    @Override
    public AddressBook saveData(AddressBook addressDetail) {
        repository.save(addressDetail);
        return addressDetail;
    }
    @Override
    public AddressBook saveDatadto(AddressDto addressDetail) {
        AddressBook newdata = new AddressBook(addressDetail);
        repository.save(newdata);
        return newdata;
    }
    @Override
    public Optional<AddressBook> FindUserById(Long Id) {
        return repository.findById(Id);
    }
    @Override
    public List<AddressBook> findAll() {
        return repository.findAll();
    }
    @Override
    public AddressBook editUserById(AddressDto dtomodel, Long Id) {
        AddressBook editdata = repository.findById(Id).orElse(null);
        if (editdata != null) {
            editdata.setFullName(dtomodel.getFullName());
            editdata.setPhoneNumber(dtomodel.getPhoneNumber());
            editdata.setEmail(dtomodel.getEmail());
            editdata.setAddress(dtomodel.getAddress());
            editdata.setCity(dtomodel.getCity());
            editdata.setState(dtomodel.getState());
            editdata.setZipcode(dtomodel.getZipcode());
            return repository.save(editdata);
        } else
            throw new AddressBookException("Id:"+Id+" is not present ");

    }
    @Override
    public void deleteById(Long Id) {
        Optional<AddressBook> addressBook = repository.findById(Id);
        if (addressBook.isPresent()){
            repository.deleteById(Id);
            sendEmail.sendEmail(addressBook.get().getEmail(), "Deleted Data", "Your Data Deleted Successfully!");
        } else throw new AddressBookException("Id:"+Id+" not present");

    }
    @Override
    public List<AddressBook> getAddressBookByemail(String email) {

        return repository.findAddressBookByemail(email);
    }
    @Override
    public List<AddressBook> getAddressBookBycity(String city) {
        return repository.findAddressBookBycity(city);
    }

    @Override
    public String insertRecord(AddressDto addressDto)  throws AddressBookException {
        AddressBook addressBook =new AddressBook(addressDto);
        repository.save(addressBook);
        String token = TokenUtil.createToken(Math.toIntExact(addressBook.getId()));
        String userData = "Your Details: \n"+addressBook.getFullName()+"\n"+addressBook.getAddress()+"\n"
                +addressBook.getCity()+"\n"+addressBook.getState()+"\n"+addressBook.getZipcode()+"\n"+
                addressBook.getPhoneNumber()+"\n"+addressBook.getEmail();
        sendEmail.sendEmail(addressBook.getEmail(),"Added Your Details", userData);
        return token;
    }
    @Override
    public List<AddressBook> getDataByToken(String token) {
        long Id=TokenUtil.decodeToken(token);
        Optional<AddressBook> isDataPresent=repository.findById(Id);
        if (isDataPresent.isPresent()){
            List<AddressBook> addressBookList=repository.findAll();
            return addressBookList;
        }else{
            throw new AddressBookException("Token is not found!");
        }
    }
    @Override
    public Optional<AddressBook> getAllUserByToken(String token) {
        int Userid = TokenUtil.decodeToken(token);
        Optional<AddressBook> existingData = repository.findById((long) Userid);
        if(existingData.isPresent()){
            return existingData;
        }else
            throw new AddressBookException("Invalid Token");
    }
}

