package br.com.fiap.sus_agendamentos.domain.consulta;

import br.com.fiap.sus_agendamentos.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.fiap.sus_agendamentos.domain.medico.Medico;
import br.com.fiap.sus_agendamentos.domain.medico.MedicoRepository;
import br.com.fiap.sus_agendamentos.domain.paciente.PacienteRepository;
import br.com.fiap.sus_agendamentos.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadorAgendamentoDeConsultas;

    public DetalhamentoConsultaDTO agendar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        if (!pacienteRepository.existsById(agendamentoConsultaDTO.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        if (agendamentoConsultaDTO != null && !medicoRepository.existsById(agendamentoConsultaDTO.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe.");
        }

        validadorAgendamentoDeConsultas.forEach(validador -> validador.validar(agendamentoConsultaDTO));

        var paciente = pacienteRepository.getReferenceById(agendamentoConsultaDTO.idPaciente());
        var medico = escolherMedico(agendamentoConsultaDTO);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }

        var consulta = new Consulta(null, medico, paciente, agendamentoConsultaDTO.data(), null);

        consultaRepository.save(consulta);

        return new DetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO agendamentoConsultaDTO) {
        if (agendamentoConsultaDTO != null) {
            return medicoRepository.getReferenceById(agendamentoConsultaDTO.idMedico());
        }

        if (agendamentoConsultaDTO.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(agendamentoConsultaDTO.especialidade(), agendamentoConsultaDTO.data());
    }

    public void cancelar(CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        if (!consultaRepository.existsById(cancelamentoConsultaDTO.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe.");
        }

        var consulta = consultaRepository.getReferenceById(cancelamentoConsultaDTO.idConsulta());
        consulta.cancelar(cancelamentoConsultaDTO.motivo());
    }

}
