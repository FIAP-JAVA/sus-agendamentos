package br.com.fiap.sus_agendamentos.paciente;

import br.com.fiap.sus_agendamentos.endereco.EnderecoDTO;
import jakarta.validation.Valid;

public record AtualizarPacienteDTO(
        Long id,
        String nome,
        String telefone,
        @Valid EnderecoDTO endereco
) {
}
