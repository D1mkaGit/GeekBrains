package ru.geekbrains.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String login;

    @Column
    @Email
    private String email;

    @Column
    private String password;

    @Transient
    private String matchingPassword;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
