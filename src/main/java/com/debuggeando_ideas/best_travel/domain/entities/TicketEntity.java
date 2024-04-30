package com.debuggeando_ideas.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketEntity implements Serializable {

    @Id
    private UUID id;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalDate purchaseDate;
    @ManyToOne
    @JoinColumn(name = "fly_id")
    private FlyEntity fly;
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = true)
    private TourEntity tour;
    @ManyToOne
    @JoinColumn(name = "custumer_id")
    private CustomerEntity custumer;
}
