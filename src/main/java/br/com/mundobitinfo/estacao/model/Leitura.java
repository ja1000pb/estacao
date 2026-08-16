package br.com.mundobitinfo.estacao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Leitura implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @NotNull
    private Boolean ativo;
    @JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dthleitura;
	private BigDecimal  umidadesolo30;
    private BigDecimal  umidadesolo60;
    private BigDecimal umidadesolo90;
    private BigDecimal temperatura;
    private BigDecimal  umidaddear;
    private BigDecimal pressao;
    private BigDecimal pressaorelativa;
    private BigDecimal luminosidade;  
	@ManyToOne
    @JoinColumn(name = "estacao_id")
    private Estacao estacao;

}
