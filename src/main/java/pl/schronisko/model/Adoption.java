package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Table(name = "adoption", indexes = {
        @Index(name = "fk_Adoption_Animal1_idx", columnList = "id_animal"),
        @Index(name = "fk_Adoption_User1_idx", columnList = "id_user")
})
@Entity
@Getter
@Setter
public class Adoption {
    @EmbeddedId
    private AdoptionId id;

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

    @Column(name = "name", length = 15)
    private String name;

    @Column(name = "surname", length = 20)
    private String surname;

}