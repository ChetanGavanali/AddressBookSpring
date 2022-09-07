package com.example.addressbookapp.model;

import com.example.addressbookapp.dto.AddressDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="AddressBook")
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long Id;
    String fullName;
    String phoneNumber;

    private String email;
    String address;

    @ElementCollection
    @CollectionTable(name="AddressBook_City",joinColumns=@JoinColumn(name="ID"))
    @Column(name="City")
    private List<String> city;
    String state;
    Long zipcode;

    public AddressBook(AddressDto dto) {
        this.fullName=dto.getFullName();
        this.phoneNumber=dto.getPhoneNumber();
        this.email= dto.getEmail();
        this.address=dto.getAddress();
        this.city= dto.getCity();
        this.state=dto.getState();
        this.zipcode=dto.getZipcode();
    }
}