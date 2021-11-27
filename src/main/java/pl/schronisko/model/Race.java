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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRace", nullable = false)
    private Integer id;

    @Column(name = "Race", nullable = false, length = 45)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idType", nullable = false)
    private Type idType;

}