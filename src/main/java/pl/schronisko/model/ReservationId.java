package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ReservationId implements Serializable {
    private static final long serialVersionUID = 8074287598248313668L;
    @Column(name = "id_animal", nullable = false)
    private Integer idAnimal;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation", nullable = false)
    private Integer idReservation;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    public ReservationId() {}
    public ReservationId(Integer idAnimal, Integer idReservation, Integer idUser) {
        this.idAnimal = idAnimal;
        this.idReservation = idReservation;
        this.idUser = idUser;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idUser, idAnimal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationId entity = (ReservationId) o;
        return Objects.equals(this.idReservation, entity.idReservation) &&
                Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idAnimal, entity.idAnimal);
    }
}