package com.aka.countries.domain;

import java.util.UUID;

public record Country(
        UUID countryId,
        String countryCode,
        String countryName,
        String coordinates) {
}
