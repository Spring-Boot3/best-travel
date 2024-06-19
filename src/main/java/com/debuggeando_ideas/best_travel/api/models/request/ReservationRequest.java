package com.debuggeando_ideas.best_travel.api.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest implements Serializable {

    @Size(min = 18, max = 20, message = "The size have to a length between 18 and 20 characters")
    @NotBlank(message = "The idClient is required")
    private String idClient;
    @Positive
    @NotNull(message = "The idHotel is required")
    private Long idHotel;
    @Min(value = 1, message = "The totalDays have to be greater than 0")
    @Max(value = 30, message = "The totalDays have to be less than 30")
    @NotNull(message = "The totalDays is required")
    private Integer totalDays;
    //@Pattern(regexp = "^(.+)@(.+)$", message = "The email is not valid")
    @Email(message = "The email is not valid")
    private String email;

}
