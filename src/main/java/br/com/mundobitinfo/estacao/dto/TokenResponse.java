package br.com.mundobitinfo.estacao.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
 
    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresIn;
    private Instant issuedAt;
    private Instant expiresAt;

}
