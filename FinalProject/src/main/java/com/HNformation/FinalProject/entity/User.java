package com.HNformation.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private UserType userType;

    public User(String lastName, String firstName, String email, UserType userType) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(email, user.email) && Objects.equals(userType.getLabel(), user.userType.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lastName, firstName, email, userType);
    }
}
