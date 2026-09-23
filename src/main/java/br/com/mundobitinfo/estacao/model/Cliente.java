package br.com.mundobitinfo.estacao.model;

import java.io.Serializable;


import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
   	@NotNull
    private Boolean ativo;
	@Size(min = 2, max = 100)
	@NotBlank(message = "campo obrigatório")
	private String nome;	
	@Size(min = 14, max = 18)
	@NotBlank(message = "campo obrigatório")
	private String cnpjcpf;	
	@Size(max = 20)
	private String ierg;	
	@Size(max = 15)
	@NotBlank(message = "campo obrigatório")
	private String telefone1;
	@Size(max = 15)
	private String telefone2;
	@Size(max = 80)
	@NotBlank(message = "campo obrigatório")
	private String logradouro;	
	@NotBlank(message = "campo obrigatório")
	private String numero;
	@Size(max = 80)
	private String complemento;
	@Size(max = 80)
	private String bairro;
	@Size(max = 80)
	@NotBlank(message = "campo obrigatório")
	private String cidade;
	@Size(max = 20)
	private String cep;
	@Size(max = 2)
	private String uf;
	@Size(max = 80)
	@NotBlank(message = "campo obrigatório")
	private String estado;
}
