package com.toylibrary.dto;

import com.toylibrary.model.MemberType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private MemberType memberType;
}
