package com.HNformation.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_type")
@NoArgsConstructor
@Getter
@Setter
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userTypeId;

    @Column(name = "label")
    private String label;

    @Transient
    @OneToMany(mappedBy = "userType", cascade = CascadeType.DETACH)
    private List<User> user;

    public UserType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", label='" + label + '\'' +
                '}';
    }
}
