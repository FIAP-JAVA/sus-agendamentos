package br.com.fiap.sus_agendamentos.domain.consulta.validacoes;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.paciente.PacienteRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(agendamentoConsultaDTO.idPaciente());

        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
