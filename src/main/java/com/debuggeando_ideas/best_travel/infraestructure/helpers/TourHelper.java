package com.debuggeando_ideas.best_travel.infraestructure.helpers;

import com.debuggeando_ideas.best_travel.domain.repositories.ReservationRepository;
import com.debuggeando_ideas.best_travel.domain.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {

    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;



}
