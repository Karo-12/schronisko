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
public class AdoptionId implements Serializable {
    private static final long serialVersionUID = 3677980064287523973L;
    @Column(name = "idAdoption", nullable = false)
    private Integer idAdoption;
    @Column(name = "idAnimal", nullable = false)
    private Integer idAnimal;
    @Column(name = "idUser", nullable = false)
    private Integer idUser;


    @Override
    public int hashCode() {
        return Objects.hash(idUser, idAnimal, idAdoption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdoptionId entity = (AdoptionId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idAnimal, entity.idAnimal) &&
                Objects.equals(this.idAdoption, entity.idAdoption);
    }
}