package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.request.TourRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TourResponse;
import com.debuggeando_ideas.best_travel.domain.entities.*;
import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.FlyRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.HotelRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.TourRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.ITourService;
import com.debuggeando_ideas.best_travel.infraestructure.helpers.TourHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    /*
     Es mala práctica que dentro de un Service se haga el llamado a otros Services
     es por eso que se hara la creacion de un Helper
    */
    private final TourHelper tourHelper;
    @Override
    public TourResponse create(TourRequest request) {
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        var flights = new HashSet<FlyEntity>();
        request.getFlights().forEach(fly -> { flights.add(flyRepository.findById(fly.getId()).orElseThrow()); });
        var hotels = new HashMap<HotelEntity, Integer>();
        request.getHotels().forEach(hotel -> { hotels.put(hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()); });

        var tourToSave = TourEntity.builder()
                .tickets(tourHelper.createTickets(flights, customer))
                .reservations(tourHelper.createReservations(hotels, customer))
                .customer(customer)
                .build();

        //Aqui ya guardamos pero nos retorna el Entity
        var tourSaved = tourRepository.save(tourToSave);

        //Lo que se hara en este caso es retornar él response
        return TourResponse.builder()
                //Se hace uso de los metodos de Stream para poder obtener los Ids de las reservaciones y tickets
                // y poder asignarlos al respons
                .reservationIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();
    }

    @Override
    public TourResponse read(Long aLong) {
        var tourFromDB = this.tourRepository.findById(aLong).orElseThrow();
        return TourResponse.builder()
                .reservationIds(tourFromDB.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourFromDB.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDB.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        var tourToDelete = tourRepository.findById(id).orElseThrow();
        this.tourRepository.delete(tourToDelete);
    }

    @Override
    public void removeTicket(Long tourId, UUID ticketId) {
        var tourUpdate = tourRepository.findById(tourId).orElseThrow();
        tourUpdate.removeTicket(ticketId);
        tourRepository.save(tourUpdate);
    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        var tourUpdate = tourRepository.findById(tourId).orElseThrow();
        var fly = flyRepository.findById(flyId).orElseThrow();
        var ticket = tourHelper.createTicket(fly, tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        tourRepository.save(tourUpdate);
        return ticket.getId();
    }

    @Override
    public void removeReservation(Long tourId, UUID reservationId) {
        var reservationRemove = tourRepository.findById(tourId).orElseThrow();
        reservationRemove.removeReservation(reservationId);
        tourRepository.save(reservationRemove);
    }

    @Override
    public UUID addReservation(Long tourId, Long hotelId, Integer totalDays) {
        var tourUpdate = tourRepository.findById(tourId).orElseThrow();
        var hotel = hotelRepository.findById(hotelId).orElseThrow();
        var reservations = tourHelper.createReservation(hotel, tourUpdate.getCustomer(), totalDays);
        tourUpdate.addReservation(reservations);
        tourRepository.save(tourUpdate);
        return reservations.getId();
    }
}
