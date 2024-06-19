package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.response.HotelResponse;
import com.debuggeando_ideas.best_travel.api.models.response.ReservationResponse;
import com.debuggeando_ideas.best_travel.domain.entities.ReservationEntity;
import com.debuggeando_ideas.best_travel.domain.entities.TourEntity;
import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.HotelRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.ReservationRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import com.debuggeando_ideas.best_travel.infraestructure.helpers.CustomerHelper;
import com.debuggeando_ideas.best_travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerHelper customerHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException("hotel"));
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException("customer"));
        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(changes_price_percent)))
                .build();
        var reservationPersisted = this.reservationRepository.save(reservationToPersist);
        customerHelper.incrase(customer.getDni(), ReservationService.class);
        return entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        var reservationFromDB = this.reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException("reservation"));
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException("hotel"));
        var reservationToUpdate = this.reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException("reservation"));

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(changes_price_percent)));

        var reservationUpdate = reservationRepository.save(reservationToUpdate);
        return entityToResponse(reservationUpdate);
    }

    @Override
    public void delete(UUID uuid) {
        var reservationToDelete = reservationRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException("reservation"));
        this.reservationRepository.delete(reservationToDelete);
    }

    @Override
    public BigDecimal findPrice(Long id) {
        var hotel = hotelRepository.findById(id).orElseThrow(() -> new IdNotFoundException("hotel"));
        return hotel.getPrice().add(hotel.getPrice().multiply(changes_price_percent));
    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var responseHotel = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), responseHotel);
        response.setHotel(responseHotel);
        return response;
    }

    public static final BigDecimal changes_price_percent = BigDecimal.valueOf(1.75);
}
