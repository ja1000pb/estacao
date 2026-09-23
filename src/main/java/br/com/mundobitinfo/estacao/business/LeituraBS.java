package br.com.mundobitinfo.estacao.business;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mundobitinfo.estacao.model.Leitura;
import br.com.mundobitinfo.estacao.repository.LeituraRepository;

@Service
public class LeituraBS {
    @Autowired
	private LeituraRepository leituraRepository;
	
	public List<Leitura>  pesquisa() {		
		return leituraRepository.findTop10ByAtivoIsTrueOrderByIdDesc();	
	}

	public List<Leitura>  pesquisaest( Long id) {		
		return leituraRepository.findTop10ByEstacaoIdAndAtivoIsTrueOrderByDthleituraDesc(id);
	}

	public List<Leitura>  pesquisar(String data) {		
		LocalDateTime dataHora = LocalDateTime.parse(data);
		return leituraRepository.findByDthleitura(dataHora);		
	}

    public Leitura buscarPeloCodigo(Long id) {
    	return leituraRepository.findById(id).get();
	}

	public Leitura criar(Leitura leitura) {
		definirPeriodoDoDia(leitura);
        calcularUmidadeSolo(leitura);
		Leitura leituraTresHorasAtras = buscarLeituraTresHorasAtras(leitura);

        definirPossibilidadeChuva(leitura,leituraTresHorasAtras);
		
		return leituraRepository.save(leitura);

	}

	public  Leitura remover(Leitura leitura) {
		 leituraRepository.deleteById(leitura.getId());
		 return leitura;
	}
	
	
	public Leitura atualizar(Long id, Leitura leitura) {
		Leitura leituraSalva = leituraRepository.findById(id).get();		
		if (leituraSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(leitura, leituraSalva, "id");
		return leituraRepository.save(leituraSalva);
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Leitura leituraSalva = buscarLeituraPeloId(id);
		leituraSalva.setAtivo(ativo);
		leituraRepository.save(leituraSalva);
	}

	private Leitura buscarLeituraPeloId(Long id) {
		Leitura leituraSalva = leituraRepository.findById(id).get();
		if (leituraSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return leituraSalva;
	}

	private void definirPeriodoDoDia(Leitura leitura) {

    if (leitura.getDthleitura() == null || leitura.getLuminosidade() == null) {
        return;
    }

    int hora = leitura.getDthleitura().getHour();
    BigDecimal luminosidade = leitura.getLuminosidade();

    BigDecimal dez = new BigDecimal("10");
    BigDecimal duzentos = new BigDecimal("200");
    BigDecimal mil = new BigDecimal("1000");
    BigDecimal dezMil = new BigDecimal("10000");

    boolean periodoDiurno = hora >= 5 && hora < 18;

    if (periodoDiurno) {

        if (luminosidade.compareTo(dezMil) >= 0) {

            leitura.setDia("dia_claro");

        } else if (luminosidade.compareTo(mil) >= 0) {

            leitura.setDia("nublado");

        } else if (luminosidade.compareTo(duzentos) >= 0) {

            leitura.setDia("dia");

        }

    } else {

        if (luminosidade.compareTo(duzentos) < 0
                && luminosidade.compareTo(dez) >= 0) {

            leitura.setDia("noite_clara");

        } else if (luminosidade.compareTo(dez) < 0) {

            leitura.setDia("noite_escura");

        }
    }
}

private Leitura buscarLeituraTresHorasAtras(Leitura leituraAtual) {

    if (leituraAtual.getEstacao() == null
            || leituraAtual.getEstacao().getId() == null
            || leituraAtual.getDthleitura() == null) {
        return null;
    }

    LocalDateTime tresHorasAtras =
            leituraAtual.getDthleitura().minusHours(3);

    return leituraRepository
            .findTopByEstacaoIdAndAtivoIsTrueAndDthleituraLessThanEqualOrderByDthleituraDesc(
                    leituraAtual.getEstacao().getId(),
                    tresHorasAtras
            )
            .orElse(null);
}

private void definirPossibilidadeChuva(
        Leitura leituraAtual,
        Leitura leituraTresHorasAtras) {

    if (leituraAtual.getTemperatura() == null
            || leituraAtual.getUmidaddear() == null
            || leituraAtual.getPressao() == null) {

        leituraAtual.setPossibilidadechuva(BigDecimal.ZERO);
        return;
    }

    double temperatura = leituraAtual.getTemperatura().doubleValue();
    double umidade = leituraAtual.getUmidaddear().doubleValue();
    double pressaoAtual = leituraAtual.getPressao().doubleValue();

    int pontos = 0;

    // ---------------------------------------
    // 1. UMIDADE RELATIVA DO AR
    // ---------------------------------------

    if (umidade >= 90) {
        pontos += 3;
    } else if (umidade >= 80) {
        pontos += 2;
    } else if (umidade >= 60) {
        pontos += 1;
    }

    // ---------------------------------------
    // 2. PONTO DE ORVALHO
    // ---------------------------------------

    double a = 17.27;
    double b = 237.7;

    double alfa =
            ((a * temperatura) / (b + temperatura))
            + Math.log(umidade / 100.0);

    double pontoOrvalho =
            (b * alfa) / (a - alfa);

    double diferenca =
            temperatura - pontoOrvalho;

    if (diferenca <= 2) {
        pontos += 3;
    } else if (diferenca <= 5) {
        pontos += 2;
    } else if (diferenca <= 8) {
        pontos += 1;
    }

    // ---------------------------------------
    // 3. TENDÊNCIA DA PRESSÃO
    // ---------------------------------------

    if (leituraTresHorasAtras != null
            && leituraTresHorasAtras.getPressao() != null) {

        double pressaoAnterior =
                leituraTresHorasAtras.getPressao().doubleValue();

        double variacaoPressao =
                pressaoAtual - pressaoAnterior;

        if (variacaoPressao <= -5) {
            pontos += 4;

        } else if (variacaoPressao <= -3) {
            pontos += 3;

        } else if (variacaoPressao <= -1) {
            pontos += 1;

        } else if (variacaoPressao > 0) {
            pontos -= 1;
        }
    }

    // ---------------------------------------
    // 4. LIMITA O ÍNDICE ENTRE 0 E 10
    // ---------------------------------------

    pontos = Math.max(0, Math.min(10, pontos));

    leituraAtual.setPossibilidadechuva(
            BigDecimal.valueOf(pontos)
    );
}

private void calcularUmidadeSolo(Leitura leitura){
    leitura.setUmidadesolo30(BigDecimal.valueOf((1023.0 - leitura.getUmidadesolo30().doubleValue()) * 100.0 / 1023.0).setScale(2, RoundingMode.HALF_UP));
    leitura.setUmidadesolo60(BigDecimal.valueOf((1023.0 - leitura.getUmidadesolo60().doubleValue()) * 100.0 / 1023.0).setScale(2, RoundingMode.HALF_UP));
    leitura.setUmidadesolo90(BigDecimal.valueOf((1023.0 - leitura.getUmidadesolo90().doubleValue()) * 100.0 / 1023.0).setScale(2, RoundingMode.HALF_UP));
}

}
