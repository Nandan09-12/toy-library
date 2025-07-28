package com.toylibrary.dto;

import com.toylibrary.model.MemberType;
import com.toylibrary.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private MemberType memberType;
    private int monthlyPoints;
    private int usedPoints;
}
