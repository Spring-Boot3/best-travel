package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.TourRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TourResponse;
import com.debuggeando_ideas.best_travel.infraestructure.services.TourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {

    private final TourService tourService;

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

    @PatchMapping(path = "/{tourId}/remove-ticket/{ticketId}")
    public ResponseEntity<TourResponse> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
        tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{tourId}/add-ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
        var response = Collections.singletonMap("ticketId", tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

}
