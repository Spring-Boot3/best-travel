package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.response.FlyResponse;
import com.debuggeando_ideas.best_travel.api.models.response.ReservationResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyService;
import com.debuggeando_ideas.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
public class FlyController {

    private final IFlyService iFlyService;

    @GetMapping
    public ResponseEntity<Page<FlyResponse>> get(@RequestParam Integer page,
                                                 @RequestParam Integer size,
                                                 @RequestHeader(required = false) SortType sortType){
        if(Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = iFlyService.realAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

}
