package com.debuggeando_ideas.best_travel.domain.entities;

import com.debuggeando_ideas.best_travel.util.AeroLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

//La implementacion Serializable se refiere a que todos los objeto que utilizen este entity va ser serializado
// esto quiere decir que va ser convertido a Byte para ser transmitido atravez de HTTP.

@Entity(name = "fly")
// Constructor sin todos los argumentos
@NoArgsConstructor
// Constructor con todos los argumentos
@AllArgsConstructor
// Getters, Setters, ToString
@Data
//Patron de diseño Builder
@Builder
public class FlyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private BigDecimal price;
    @Column(length = 20)
    private String originName;
    @Column(length = 20)
    private String destinyName;
    @Enumerated(EnumType.STRING)
    private AeroLine aeroLine;

}
