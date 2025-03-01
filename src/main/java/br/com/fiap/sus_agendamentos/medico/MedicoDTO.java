package br.com.fiap.sus_agendamentos.medico;

import br.com.fiap.sus_agendamentos.endereco.EnderecoDTO;

public record MedicoDTO(

        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        EnderecoDTO endereco

) {
}
