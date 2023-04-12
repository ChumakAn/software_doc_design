package com.example.software_doc_design.domain;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Nullable
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String login;
    private String password;
}
