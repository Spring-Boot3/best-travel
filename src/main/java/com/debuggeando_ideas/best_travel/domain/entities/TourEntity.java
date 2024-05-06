package com.debuggeando_ideas.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tour")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "tour"
    )
    private Set<ReservationEntity> reservations;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "tour"
    )
    private Set<TicketEntity> tickets;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;

    public void addTicket(TicketEntity ticket){
        if(Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
        this.tickets.add(ticket);
    }

    public void removeTicket(UUID id){
        //Aquí es una funcion lambda que removera el ticket solo si se cumple la condicion
        if(Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
        this.tickets.removeIf(ticket -> ticket.getId().equals(id));
    }

    public void updateTickets(){
        this.tickets.forEach(ticket -> ticket.setTour(this));
    }

    public void addReservation(ReservationEntity reservation){
        if(Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
        this.reservations.add(reservation);
    }

    public void removeReservation(UUID id){
        //Aquí es una funcion lambda que removera el ticket solo si se cumple la condicion
        if(Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
        this.reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public void updateReservation(){
        this.reservations.forEach(reservation -> reservation.setTour(this));
    }
}
