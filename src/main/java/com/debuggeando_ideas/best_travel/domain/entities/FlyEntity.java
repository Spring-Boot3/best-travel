package com.debuggeando_ideas.best_travel.domain.entities;

import com.debuggeando_ideas.best_travel.util.enums.AeroLine;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

//La implementacion Serializable se refiere a que todos los objeto que utilizen este entity va a ser serializado
// esto quiere decir que va a ser convertido a Byte para ser transmitido atravez de HTTP.

@Entity(name = "fly")
// Constructor sin argumentos
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
    //Estas dos etiquetas las mandamos a llamar
    // para evitar la recursividad infinita del metodo ToString
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL ,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "fly"
    )
    private Set<TicketEntity> tickets;

}
