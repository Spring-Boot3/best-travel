package com.debuggeando_ideas.best_travel.api.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketResponse implements Serializable {

    private UUID id;
    //Esto nos sirve para darle un formato a las fechas
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime arrivalDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private LocalDate puchaseDate;
    private BigDecimal price;
    private FlyResponse fly;

}
