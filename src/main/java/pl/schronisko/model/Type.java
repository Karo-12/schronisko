package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "type")
@Entity
@Getter
@Setter
public class Type {
    @Id
    @Column(name = "idType", nullable = false)
    private Integer id;

    @Column(name = "Type", nullable = false, length = 45)
    private String type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idRace", nullable = false)
    private Race idRace;


}