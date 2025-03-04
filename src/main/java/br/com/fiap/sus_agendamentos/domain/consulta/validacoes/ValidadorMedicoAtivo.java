package br.com.fiap.sus_agendamentos.domain.consulta.validacoes;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.medico.MedicoRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;

public class ValidadorMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        //escolha do médico opcional
        if (agendamentoConsultaDTO.idMedico() == null) {
            return;
        }

        var medidoEstaAtivo = medicoRepository.findAtivoById(agendamentoConsultaDTO.idMedico());

        if (!medidoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada, pois o médico não está ativo.");
        }
    }
}
