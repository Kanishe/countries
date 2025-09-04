package com.aka.countries.controller;

import com.aka.countries.domain.Country;
import com.aka.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountriesController {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    @PostMapping("/create")
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PatchMapping("/update")
    public Country update(@RequestBody Country country) {
        return countryService.updateByCode(country.countryCode(), country.countryName());
    }
}
