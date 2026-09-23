package br.com.mundobitinfo.estacao.model;

import java.io.Serializable;
import java.time.LocalDateTime;



import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cultura_cliente  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   	@NotNull
    private Boolean ativo;
    @JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dtplantio;
    private String adubacao;
    private String espacentrelinhas;
    private String espacplantas;
    private String arcodecultivo;
    private String profundeoperacao;
    @ManyToOne
    @JoinColumn(name = "cultura_id")
    private Cultura cultura;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
