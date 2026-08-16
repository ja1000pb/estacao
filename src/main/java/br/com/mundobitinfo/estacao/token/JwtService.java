package br.com.mundobitinfo.estacao.token;

import java.time.Instant;
import java.util.List;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.dto.TokenResponse;
import br.com.mundobitinfo.estacao.model.Usuario;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    private static final long ACCESS_TOKEN_EXPIRATION = 3600L;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public TokenResponse gerarToken(Usuario usuario) {

        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(ACCESS_TOKEN_EXPIRATION);

         List<String> permissoes = usuario.getPermissoes()
            .stream()
            .map(permissao -> permissao.getDescricao())
            .toList();

        JwsHeader jwsHeader = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("estacao-api")
                .issuedAt(agora)
                .expiresAt(expiracao)
                .subject(usuario.getEmail())
                .claim("authorities", permissoes)
                .build();

        String accessToken = jwtEncoder
                .encode(JwtEncoderParameters.from(jwsHeader, claims))
                .getTokenValue();

        return new TokenResponse(
                accessToken,
                "Bearer",
                ACCESS_TOKEN_EXPIRATION,
                agora,
                expiracao
        );
    }
}