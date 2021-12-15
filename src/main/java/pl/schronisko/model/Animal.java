package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "animal", indexes = {
        @Index(name = "fk_Animal_Race1_idx", columnList = "idRace"),
        @Index(name = "fk_Animal_User1_idx", columnList = "idUser")
})
@Entity
@Getter
@Setter
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnimal", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "arrivalDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @Column(name = "cageNumber", nullable = false)
    private Integer cageNumber;

    @Lob
    @Column(name = "status", nullable = false)
    private String status; // wolny, zarezerwowany, zaadoptowany

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idRace", nullable = false)
    private Race idRace;

}