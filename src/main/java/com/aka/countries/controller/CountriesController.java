package com.aka.countries.controller;

import com.aka.countries.domain.Country;
import com.aka.countries.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable("id") String id) {
        return countryService.byId(id);
    }

    @PostMapping("/create")
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PatchMapping("/update")
    public Country update(@RequestBody Country country) {
        return countryService.updateByCode(country.countryCode(), country.countryName());
    }

//    Важно! про работу @ExceptionHandler
//    Приоритет: локальный > глобальный
//    Если в контроллере есть @ExceptionHandler, он перекрывает глобальный.
//    Spring сначала ищет в самом контроллере, потом в @ControllerAdvice (GlobalExceptionHandler.class).

//    @Deprecated
//    @ExceptionHandler(NotFoundCountryByIdException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public void handleNotFoundCountryException(HttpServletRequest httpServlet) {
//        log.error(httpServlet.getRequestURI());
//    }
}
