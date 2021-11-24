package pl.schronisko.model;

import javax.persistence.*;

@Table(name = "race", indexes = {
        @Index(name = "fk_Race_Type1_idx", columnList = "idType")
})
@Entity
public class Race {
    @EmbeddedId
    private RaceId id;

    @Column(name = "Race", nullable = false, length = 45)
    private String race;


}