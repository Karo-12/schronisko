package pl.schronisko.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;


}