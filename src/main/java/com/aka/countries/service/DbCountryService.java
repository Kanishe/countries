package com.aka.countries.service;

import com.aka.countries.data.entity.CountryEntity;
import com.aka.countries.data.repository.CountryRepository;
import com.aka.countries.domain.Country;
import com.aka.countries.exception.NotFoundCountryByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
                            countryEntity.getId(),
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
                savedCountry.getId(),
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
                foundedCountry.getId(),
                foundedCountry.getName(),
                foundedCountry.getIsoCode(),
                foundedCountry.getCoordinates());
    }

    @Override
    public Country byId(String id) {
        return countryRepository.findById(UUID.fromString(id))
                .map(countryEntity -> {
                    return new Country(
                            countryEntity.getId(),
                            countryEntity.getIsoCode(),
                            countryEntity.getName(),
                            countryEntity.getCoordinates());
                }).orElseThrow(() -> new NotFoundCountryByIdException(id));
    }
}
