package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "animal", indexes = {
        @Index(name = "fk_Animal_Type1_idx", columnList = "idType"),
        @Index(name = "fk_Animal_User1_idx", columnList = "idUser")
})
@Entity
@Getter
@Setter
public class Animal {
    @EmbeddedId
    private AnimalId id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "arivalDate", nullable = false)
    private Instant arivalDate;

    @Column(name = "cageNumber", nullable = false)
    private Integer cageNumber;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idType", nullable = false)
    private Type idType;

    public Type getIdType() {
        return idType;
    }

    public void setIdType(Type idType) {
        this.idType = idType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(Integer cageNumber) {
        this.cageNumber = cageNumber;
    }

    public Instant getArivalDate() {
        return arivalDate;
    }

    public void setArivalDate(Instant arivalDate) {
        this.arivalDate = arivalDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalId getId() {
        return id;
    }

    public void setId(AnimalId id) {
        this.id = id;
    }
}