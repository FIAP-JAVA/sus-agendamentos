package br.com.fiap.sus_agendamentos.domain.consulta.validacoes;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.ConsultaRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository
                .existsByMedicoIdAndData(agendamentoConsultaDTO.idMedico(), agendamentoConsultaDTO.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
