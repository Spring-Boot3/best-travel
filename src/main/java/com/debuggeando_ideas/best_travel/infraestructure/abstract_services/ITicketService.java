package com.debuggeando_ideas.best_travel.infraestructure.abstract_services;

import com.debuggeando_ideas.best_travel.api.models.request.TicketRequest;
import com.debuggeando_ideas.best_travel.api.models.response.TicketResponse;

import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID> {
}
