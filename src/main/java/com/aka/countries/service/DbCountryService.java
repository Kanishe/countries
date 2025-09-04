package com.aka.countries.service;

import com.aka.countries.data.entity.CountryEntity;
import com.aka.countries.data.repository.CountryRepository;
import com.aka.countries.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(countryEntity -> {
                    return new Country(
                            countryEntity.getIsoCode(),
                            countryEntity.getName(),
                            countryEntity.getCoordinates());
                })
                .toList();
    }

    @Override
    public Country create(Country country) {
        CountryEntity savedCountry = countryRepository.save(
                new CountryEntity(
                        null,
                        country.countryName(),
                        country.countryCode(),
                        null
                )
        );
        return new Country(
                savedCountry.getName(),
                savedCountry.getIsoCode(),
                savedCountry.getCoordinates());
    }

    @Override
    public Country updateByCode(String countryCode, String newName) {
        CountryEntity foundedCountry = countryRepository
                .findByIsoCode(countryCode)
                .orElseThrow();

        foundedCountry.setName(newName);
        countryRepository.save(foundedCountry);
        return new Country(
                foundedCountry.getName(),
                foundedCountry.getIsoCode(),
                foundedCountry.getCoordinates());
    }
}
