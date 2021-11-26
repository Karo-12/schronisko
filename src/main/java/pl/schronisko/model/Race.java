package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "race")
@Entity
@Getter
@Setter
public class Race {
    @Id
    @Column(name = "id_race", nullable = false)
    private Integer id;

    @Column(name = "Race", nullable = false, length = 45)
    private String race;



}