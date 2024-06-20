package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.request.TicketRequest;
import com.debuggeando_ideas.best_travel.api.models.response.ErrorsResponse;
import com.debuggeando_ideas.best_travel.api.models.response.ReservationResponse;
import com.debuggeando_ideas.best_travel.api.models.response.TicketResponse;
import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.HotelRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.ReservationRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import com.debuggeando_ideas.best_travel.infraestructure.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
@Tag(name = "Reservation", description = "Reservation Controller")
public class ReservationController {

    private final IReservationService iReservationService;

    @ApiResponse(
            responseCode = "400",
            description = "When the reservation have a fields invalid we response this",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Create a new reservation")
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request){
        return ResponseEntity.ok(iReservationService.create(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(iReservationService.read(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> put(@Valid @RequestBody ReservationRequest request, @PathVariable UUID id){
        return ResponseEntity.ok(iReservationService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.iReservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam Long reservationId){
        return ResponseEntity.ok(Collections.singletonMap("price", iReservationService.findPrice(reservationId)));
    }



}
