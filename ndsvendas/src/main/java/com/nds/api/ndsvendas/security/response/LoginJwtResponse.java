package com.nds.api.ndsvendas.security.response;

import com.nds.api.ndsvendas.dtos.UtilizadorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginJwtResponse {

    private String token;
    private String email;
    private boolean firstLogin;
    private UtilizadorDTO userLogado;
    
}