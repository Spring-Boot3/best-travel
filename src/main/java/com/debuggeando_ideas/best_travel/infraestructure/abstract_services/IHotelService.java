package com.debuggeando_ideas.best_travel.infraestructure.abstract_services;

import com.debuggeando_ideas.best_travel.api.models.response.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogoService<HotelResponse> {

    Set<HotelResponse> readGreaterThan(Integer rating);

}
