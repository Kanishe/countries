package com.aka.countries.service;


import com.aka.countries.CountriesRequest;
import com.aka.countries.CountriesServiceGrpc;
import com.aka.countries.QuantityCountriesResponse;
import com.aka.countries.domain.Country;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CountryGrpcService extends CountriesServiceGrpc.CountriesServiceImplBase {
    private final DbCountryService dbCountryService;

    @Autowired
    public CountryGrpcService(DbCountryService dbCountryService) {
        this.dbCountryService = dbCountryService;
    }

    @Override
    public StreamObserver<CountriesRequest> addCountries(StreamObserver<QuantityCountriesResponse> responseObserver) {

        return new StreamObserver<CountriesRequest>() {
            private int count = 0;

            @Override
            public void onNext(CountriesRequest request) {
                Country country = new Country(
                        UUID.fromString(request.getId()),
                        request.getName(),
                        request.getIsoCode(), null);
                dbCountryService.create(country);
                count++;
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                QuantityCountriesResponse quantityCountriesResponse = QuantityCountriesResponse
                        .newBuilder()
                        .setCount(count)
                        .build();
                responseObserver.onNext(quantityCountriesResponse);
                responseObserver.onCompleted();
            }
        };

    }
}
