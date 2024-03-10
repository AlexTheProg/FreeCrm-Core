package com.axc.persistence.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String zipCode;

    @Column
    private String country;

    @Column
    private String region;
}
