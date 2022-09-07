package com.example.addressbookapp.repo;

import com.example.addressbookapp.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo extends JpaRepository<AddressBook, Long> {
    @Query(value="SELECT * FROM address_book WHERE userid = userid AND email = :email", nativeQuery=true)
    List<AddressBook> findAddressBookByemail(String email);
    @Query(value="SELECT * FROM address_book,address_book_City WHERE userid = id AND city = :city", nativeQuery=true)
    List<AddressBook> findAddressBookBycity(String city);


}