package com.uade.tpo.demo.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private String rol;

    private String email;

    private String name;

    private String lastName;
}
