package com.debuggeando_ideas.best_travel.infraestructure.abstract_services;

import com.debuggeando_ideas.best_travel.api.models.response.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogoService<FlyResponse>{

    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);

}
