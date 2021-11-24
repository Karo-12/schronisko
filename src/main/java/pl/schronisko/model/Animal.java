package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "animal", indexes = {
        @Index(name = "fk_Animal_Type1_idx", columnList = "idType"),
        @Index(name = "fk_Animal_User1_idx", columnList = "idUser")
})
@Entity
@Getter
@Setter
public class Animal {
    @EmbeddedId
    private AnimalId id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "arivalDate", nullable = false)
    private Instant arivalDate;

    @Column(name = "cageNumber", nullable = false)
    private Integer cageNumber;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idType", nullable = false)
    private Type idType;

}