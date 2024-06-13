package com.debuggeando_ideas.best_travel.infraestructure.helpers;

import com.debuggeando_ideas.best_travel.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


// Se define esta clase como componente porque no estamos trabajando con un servicios
@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {

    private final CustomerRepository customerRepository;

    //Se definio el parametro class esto para crear un enumerador, porque si no se
    // tendria que hacer uno por cada uno nombre de clase
    public void incrase(String customerId, Class<?> type){
        var customerToUpdate = customerRepository.findById(customerId).orElseThrow();
        switch (type.getSimpleName()){
            case "TourService": customerToUpdate.setTotalTours(customerToUpdate.getTotalTours() + 1);
            case "TicketService": customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights() + 1);
            case "ReservationService": customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings() + 1);
        }
        customerRepository.save(customerToUpdate);
    }

}
