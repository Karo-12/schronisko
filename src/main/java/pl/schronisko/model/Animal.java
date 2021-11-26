package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "animal", indexes = {
        @Index(name = "fk_Animal_Type1_idx", columnList = "id_type"),
        @Index(name = "fk_Animal_User1_idx", columnList = "id_user")
})
@Entity
@Getter
@Setter
public class Animal {
    @EmbeddedId
    private AnimalId id;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "arival_date", nullable = false)
    private Instant arivalDate;

    @Column(name = "cage_number", nullable = false)
    private Integer cageNumber;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    private Type idType;


}