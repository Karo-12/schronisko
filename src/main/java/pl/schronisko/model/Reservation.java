package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Table(name = "reservation", indexes = {
        @Index(name = "fk_Reservation_User1_idx", columnList = "id_user"),
        @Index(name = "fk_Reservation_Animal1_idx", columnList = "id_animal")
})
@Entity
@Getter
@Setter
public class Reservation {
    @EmbeddedId
    private ReservationId id;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

}