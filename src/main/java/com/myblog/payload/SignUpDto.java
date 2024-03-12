package com.myblog.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpDto {

    private String name;
    private String username;
    private String email;
    private String password;
    private String roleType;
}
