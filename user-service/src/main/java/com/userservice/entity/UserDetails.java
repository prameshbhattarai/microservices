package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "street_number", length = 10)
    private String streetNumber;

    @Column(name = "zip_code", length = 6)
    private String zipCode;

    @Column(name = "country", length = 30)
    private String country;

    @OneToOne(mappedBy = "userDetails")
    @JsonIgnore
    private User user;
}
