package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.TourRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TourResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {

    private final ITourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> create(@RequestBody TourRequest request){
        return ResponseEntity.ok(tourService.create(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TourResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.read(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TourResponse> deleteById(@PathVariable Long id){
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
