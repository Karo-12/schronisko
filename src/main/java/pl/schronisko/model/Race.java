package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "race", indexes = {
        @Index(name = "fk_Race_Type1_idx", columnList = "idType")
})
@Entity
@Getter
@Setter
public class Race {
    @EmbeddedId
    private RaceId id;

    @Column(name = "Race", nullable = false, length = 45)
    private String race;

}