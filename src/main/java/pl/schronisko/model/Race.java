package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "race")
@Entity
@Getter
@Setter
public class Race {
    @Id
    @Column(name = "idRace", nullable = false)
    private Integer id;

    @Column(name = "Race", nullable = false, length = 45)
    private String race;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idType", nullable = false)
    private Type idType;

}