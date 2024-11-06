package group14.backend.lms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Entity
@Table
public class Admin extends User {
    public Admin(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
