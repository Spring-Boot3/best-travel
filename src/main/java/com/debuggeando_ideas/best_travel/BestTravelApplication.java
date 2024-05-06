package com.debuggeando_ideas.best_travel;

import com.debuggeando_ideas.best_travel.domain.entities.ReservationEntity;
import com.debuggeando_ideas.best_travel.domain.entities.TicketEntity;
import com.debuggeando_ideas.best_travel.domain.entities.TourEntity;
import com.debuggeando_ideas.best_travel.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

	private final FlyRepository flyRepository;

	private final HotelRepository hotelRepository;

	private final CustomerRepository customerRepository;

	private final ReservationRepository reservationRepository;

	private final TicketRepository ticketRepository;

	private final TourRepository tourRepository;

	BestTravelApplication(
			FlyRepository flyRepository,
			HotelRepository hotelRepository,
			CustomerRepository customerRepository,
			ReservationRepository reservationRepository,
			TicketRepository ticketRepository,
			TourRepository tourRepository
	) {
		this.flyRepository = flyRepository;
		this.hotelRepository = hotelRepository;
		this.customerRepository = customerRepository;
		this.reservationRepository = reservationRepository;
		this.ticketRepository = ticketRepository;
		this.tourRepository = tourRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(System.out::println);

		var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
		log.info("Client name: " + customer.getFullName());

		 var fly = flyRepository.findById(11L).orElseThrow();

		 var hotel = hotelRepository.findById(3L).orElseThrow();
		 log.info("Hotel " + hotel.getName());

		 var tour = TourEntity.builder().customer(customer).build();

		 var ticket = TicketEntity.builder()
				 .id(UUID.randomUUID())
				 .price(fly.getPrice().multiply(BigDecimal.TEN))
				 .arrivalDate(LocalDate.now())
				 .departureDate(LocalDate.now())
				 .purchaseDate(LocalDate.now())
				 .customer(customer)
				 .tour(tour)
				 .fly(fly)
				 .build();

		 var reservation = ReservationEntity.builder()
				 .id(UUID.randomUUID())
				 .dateTimeReservation(LocalDateTime.now())
				 .dateEnd(LocalDate.now())
				 .dateStart(LocalDate.now())
				 .hotel(hotel)
				 .customer(customer)
				 .tour(tour)
				 .totalDays(1)
				 .price(hotel.getPrice().multiply(BigDecimal.TEN))
				 .build();

		 System.out.println("---SAVING---");
		 tour.addReservation(reservation);
		 tour.updateReservation();

		 tour.addTicket(ticket);
		 tour.updateTickets();
		 this.tourRepository.save(tour);
	}
}
