package br.com.fiap.sus_agendamentos.domain.consulta.validacoes;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.ConsultaRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        var primeiroHorario = agendamentoConsultaDTO.data().withHour(7);
        var ultimoHorario = agendamentoConsultaDTO.data().withHour(18);
        var pacientePossuiuOutraConsultaNoDia = consultaRepository
                .existsByPacienteIdAndDataBetween(agendamentoConsultaDTO.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiuOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}


