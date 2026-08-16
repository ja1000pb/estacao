package br.com.mundobitinfo.estacao.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Usuario;
import br.com.mundobitinfo.estacao.repository.UsuarioRepository;

@Service
public class UsuarioBS {
    
  	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario){
		usuarioRepository.save(usuario);
	}
	
	public String getSenhaByUsuario(Usuario usuario) {
		if(usuario == null) {
			return null;
		}
		return usuarioRepository.getSenhaByNomeOrId(usuario.getId(), usuario.getSenha());
	}
	
	public Usuario getByNomeOrEmail(String value) {
		return usuarioRepository.findByNomeOrEmail(value, value).get();
	}
}


