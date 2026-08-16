package br.com.mundobitinfo.estacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.business.UsuarioBS;
import br.com.mundobitinfo.estacao.exception.EstacaoException;
import br.com.mundobitinfo.estacao.model.Usuario;
import br.com.mundobitinfo.estacao.util.EstacaoUtil;

@Service
public class UsuarioCtrl {
    	@Autowired
	private UsuarioBS bs;

	final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	public void salvar(Usuario usuario) {
		tratarSenha(usuario);
		bs.salvar(usuario);
	}

	public boolean confirmarSenha(Usuario usuario) {
		if (usuario == null || usuario.getSenha() == null) {
			return false;
		}

		boolean retorno = EstacaoUtil.senhasIguais(usuario.getSenha(), bs.getSenhaByUsuario(usuario));
		if (retorno) {
			return retorno;
		} else {
			throw new EstacaoException("Senha Incorreta", "Senha Incoreta");
		}
	}
	
	public Boolean esqueceuSenha(Usuario usuario) {
		if (usuario == null || usuario.getSenha() == null || usuario.getSenha().trim().equals("") ){
			throw new EstacaoException("dados insuficiente para alterar a senha", "falha ao acessar usuario");
		}
		Usuario usuarioBanco = bs.getByNomeOrEmail(usuario.getNome());
		if (usuarioBanco != null) {
			usuarioBanco.setSenha(usuario.getSenha());
			salvar(usuarioBanco);
			return true;
		} else {
			throw new EstacaoException("CPF Não encotrado", "falha ao acessar usuario");
		}
	}
	
	public void tratarSenha(Usuario usuario) {
		if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			usuario.setSenha("abc123");
			tratarEncodamentoSenha(usuario);
		} else {
			tratarEncodamentoSenha(usuario);
		}
	}
	
	public void tratarEncodamentoSenha(Usuario usuario) {
		if (encoder.upgradeEncoding(usuario.getSenha())) {
			usuario.setSenha(encoder.encode(usuario.getSenha()));
		}
	}
	
	
	public Usuario getByNomeOrEmail(String value) {
		return bs.getByNomeOrEmail(value);
	}

}
