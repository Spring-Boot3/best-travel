package com.debuggeando_ideas.best_travel.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

//En esta ocacion importamos el paquete lombok.experimental.SuperBuilder para poder hacer uso de la anotacion @SuperBuilder
//por la razon de que esta clase es una clase padre de la cual heredaran otras clases y queremos que estas clases hijas
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse implements Serializable {

    private String status;
    private Integer code;

}
