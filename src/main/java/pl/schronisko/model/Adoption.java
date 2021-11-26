package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

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
    private Instant birthday;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "name", length = 15)
    private String name;

    @Column(name = "surname", length = 20)
    private String surname;

}