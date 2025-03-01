package br.com.fiap.sus_agendamentos.domain.paciente;

import br.com.fiap.sus_agendamentos.domain.endereco.EnderecoDTO;
import jakarta.validation.Valid;

public record AtualizarPacienteDTO(
        Long id,
        String nome,
        String telefone,
        @Valid EnderecoDTO endereco
) {
}
