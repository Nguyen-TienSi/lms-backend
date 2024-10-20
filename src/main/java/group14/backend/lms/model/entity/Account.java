package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(value = EnumType.STRING)
    protected Role role;

    protected String firstName;

    protected String lastName;

    protected Boolean gender;

    protected Date birthDate;

    protected String email;

    protected String password;

    protected String phone;

    protected Boolean active;
}

