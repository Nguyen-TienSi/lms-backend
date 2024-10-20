package group14.backend.lms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin extends Account {
    public Admin() {
        setRole(Role.ADMINISTRATOR);
    }
}
