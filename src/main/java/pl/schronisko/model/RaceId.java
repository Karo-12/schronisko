package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter

public class RaceId implements Serializable {
    private static final long serialVersionUID = -4073786171537406189L;
    @Column(name = "idRace", nullable = false)
    private Integer idRace;
    @Column(name = "idType", nullable = false)
    private Integer idType;


    @Override
    public int hashCode() {
        return Objects.hash(idType, idRace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RaceId entity = (RaceId) o;
        return Objects.equals(this.idType, entity.idType) &&
                Objects.equals(this.idRace, entity.idRace);
    }
}