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

    /**
     * Estas anotaciones se utilizan para asegurar
     * que las relaciones bidireccionales entre
     * TourEntity y TicketEntity / ReservationEntity
     * se mantengan correctamente cuando se persisten o eliminan las entidades.
     */
    @PrePersist
    @PreRemove
    public void updateFK(){
        if(Objects.nonNull(this.tickets)) this.tickets.forEach(ticket -> ticket.setTour(this));
        if(Objects.nonNull(this.reservations)) this.reservations.forEach(reservation -> reservation.setTour(this));
    }

    public void removeTicket(UUID id){
        this.tickets.forEach(ticket -> {
            if(ticket.getId().equals(id)){
                ticket.setTour(null);
            }
        });
    }

    public void addTicket(TicketEntity ticket){
        if(Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
        this.tickets.add(ticket);
        if(Objects.nonNull(this.tickets)) this.tickets.forEach(tickets -> tickets.setTour(this));

    }

}
