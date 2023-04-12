package com.example.software_doc_design.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Data
@RequiredArgsConstructor
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"login", "password"})})
@Builder
@AllArgsConstructor
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}
