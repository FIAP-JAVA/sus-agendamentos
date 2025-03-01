package br.com.fiap.sus_agendamentos.paciente;

public record ListarPacienteDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public ListarPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
