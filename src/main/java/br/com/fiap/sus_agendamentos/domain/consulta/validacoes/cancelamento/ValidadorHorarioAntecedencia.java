package br.com.fiap.sus_agendamentos.domain.consulta.validacoes.cancelamento;

import br.com.fiap.sus_agendamentos.domain.consulta.CancelamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.ConsultaRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        var consulta = repository.getReferenceById(cancelamentoConsultaDTO.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h.");
        }
    }
}
