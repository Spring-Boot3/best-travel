package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.response.ReservationResponse;
import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.HotelRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.ReservationRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import com.debuggeando_ideas.best_travel.infraestructure.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService iReservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(iReservationService.create(request));
    }



}
