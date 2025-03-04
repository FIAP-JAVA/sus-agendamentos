package br.com.fiap.sus_agendamentos.domain.consulta.validacoes.cancelamento;

import br.com.fiap.sus_agendamentos.domain.consulta.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(CancelamentoConsultaDTO cancelamentoConsultaDTO);
}
