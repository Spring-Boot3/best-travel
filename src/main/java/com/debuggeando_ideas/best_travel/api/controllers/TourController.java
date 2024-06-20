package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.TourRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TourResponse;
import com.debuggeando_ideas.best_travel.infraestructure.services.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
@Tag(name = "Tour", description = "Tour Controller")
public class TourController {

    private final TourService tourService;

    @Operation(summary = "Create a new tour")
    @PostMapping
    public ResponseEntity<TourResponse> create(@RequestBody TourRequest request){
        return ResponseEntity.ok(tourService.create(request));
    }

    @Operation(summary = "Get all tours")
    @GetMapping(path = "/{id}")
    public ResponseEntity<TourResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.read(id));
    }

    @Operation(summary = "Delete a tour by id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TourResponse> deleteById(@PathVariable Long id){
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a tour by id")
    @PatchMapping(path = "/{tourId}/remove-ticket/{ticketId}")
    public ResponseEntity<TourResponse> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
        tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a ticket to a tour by id")
    @PatchMapping(path = "/{tourId}/add-ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
        var response = Collections.singletonMap("ticketId", tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a reservation to a tour by id")
    @PatchMapping(path = "/{tourId}/remove-reservation/{reservationId}")
    public ResponseEntity<TourResponse> deleteReservation(@PathVariable Long tourId, @PathVariable UUID reservationId){
        tourService.removeReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a reservation to a tour by id")
    @PatchMapping(path = "/{tourId}/add-reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> postReservation(@PathVariable Long tourId, @PathVariable Long hotelId, @RequestParam Integer totalDays){
        var response = Collections.singletonMap("ticketId", tourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }

}
