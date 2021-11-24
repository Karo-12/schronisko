package pl.schronisko.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "idUser", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "password", nullable = false, length = 15)
    private String password;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "birthday", nullable = false)
    private Instant birthday;

    @Column(name = "permission", nullable = false)
    private Integer permission;
}