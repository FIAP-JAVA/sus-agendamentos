package br.com.fiap.sus_agendamentos.medico;

import br.com.fiap.sus_agendamentos.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
