package com.aka.countries.service;

import com.aka.countries.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country create(Country country);

    Country updateByCode(String countryCode, String newName);

}
