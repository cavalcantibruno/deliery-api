package com.foop.delivery.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {
    @Column(name = "address_zipcode")
    private String zipcode;
    @Column(name = "address_street")
    private String street;
    @Column(name = "address_number")
    private String number;
    @Column(name = "address_complement")
    private String complement;
    @Column(name = "address_district")
    private String district;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;
    @Column(name = "address_country")
    private String country;
}
