package br.com.fiap.sus_agendamentos.domain.consulta.validacoes.agendamento;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDTO agendamentoConsultaDTO);
}
