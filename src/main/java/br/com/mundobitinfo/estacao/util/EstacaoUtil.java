package br.com.mundobitinfo.estacao.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import br.com.mundobitinfo.estacao.model.Usuario;
import br.com.mundobitinfo.estacao.exception.EstacaoException;


public class EstacaoUtil {
    private static PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	// recebe seha não encodada e compara com senha encodada
	public static boolean senhasIguais(String senha, String senhaEncodada) {
		return encoder.matches(senha, senhaEncodada);
	}

	/* decofica o token e retorna a propriedade passada em Json*/
    public static Object decodeTokenRequest(String propriedade) {
        try {
            Jwt jwt = getJwtToken();
            return jwt.getClaim(propriedade);
        } catch (Exception e) {
            throw new EstacaoException(
                    "Falha ao obter informações do usuário logado",
                    e.getMessage()
            );
        }
    }

    public static Usuario getUsuarioTokenRequest() {
        try {
            Jwt jwt = getJwtToken();

            Usuario usuario = new Usuario();
            usuario.setEmail(jwt.getSubject());

            return usuario;

        } catch (Exception e) {
            throw new EstacaoException(
                    "Falha ao obter informações do usuário logado",
                    e.getMessage()
            );
        }
    }

    private static Jwt getJwtToken() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new EstacaoException(
                    "Usuário não autenticado",
                    "Token JWT não encontrado"
            );
        }

        return jwt;
    }
}
