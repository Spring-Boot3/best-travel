package com.debuggeando_ideas.best_travel.infraestructure.abstract_services;

// Generamos una interfaz generica para todos nuestros Servicios
public interface CrudService<RQ,RS,ID> {

    RS create(RQ request);

    RS read(ID id);

    RS update(RQ request, ID id);

    void delete(ID id);
}
