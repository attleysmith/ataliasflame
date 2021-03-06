package hu.asgames.domain.entities;

import hu.asgames.domain.enums.RegistrationState;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.11.
 */
@Entity
@Table(name = "registration")
public class Registration extends IdentifiedEntityBase {

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "registration_code", nullable = false)
    private String registrationCode;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private RegistrationState state;

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public RegistrationState getState() {
        return state;
    }

    public void setState(RegistrationState state) {
        this.state = state;
    }

    // General methods

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Registration{");
        sb.append("user.id=").append(user.getId());
        sb.append(", registrationCode='").append(registrationCode).append('\'');
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", confirmationDate=").append(confirmationDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
