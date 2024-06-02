package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.request.TourRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TourResponse;
import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.FlyRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.HotelRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.TourRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.ITourService;
import com.debuggeando_ideas.best_travel.infraestructure.helpers.TourHelper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        return null;
    }

    @Override
    public TourResponse read(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void removeTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(UUID flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(UUID reservationId, Long tourId) {
        return null;
    }
}
