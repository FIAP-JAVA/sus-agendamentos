package br.com.fiap.sus_agendamentos.domain.medico;

import br.com.fiap.sus_agendamentos.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
