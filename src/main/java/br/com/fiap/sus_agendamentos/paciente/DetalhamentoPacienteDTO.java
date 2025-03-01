package br.com.fiap.sus_agendamentos.paciente;

import br.com.fiap.sus_agendamentos.endereco.Endereco;

public record DetalhamentoPacienteDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DetalhamentoPacienteDTO(Paciente paciente) {
        this(
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getCpf(),
            paciente.getEndereco()
        );
    }
}
