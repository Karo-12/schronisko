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
public class AnimalId implements Serializable {

    private static final long serialVersionUID = -3439671743016515633L;
    @Column(name = "id_animal", nullable = false)
    private Integer idAnimal;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    public AnimalId() {}
    public AnimalId(Integer idAnimal, Integer idUser) {
        this.idAnimal = idAnimal;
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idAnimal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalId entity = (AnimalId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idAnimal, entity.idAnimal);
    }
}