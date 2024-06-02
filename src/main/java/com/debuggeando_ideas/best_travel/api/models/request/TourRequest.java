package com.debuggeando_ideas.best_travel.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest implements Serializable {

    public String customerId;
    private Set<TourFlyRequest> flights;
    private Set<TourHotelRequest> hotels;

}
