package com.martin.api.persistence;

import lombok.Builder;

@Builder
public record Weather(
    String address,
    String timezone,
    String description,
    String sunset,
    String sunrise,
    String dateTime,
    double temp
) {

}
