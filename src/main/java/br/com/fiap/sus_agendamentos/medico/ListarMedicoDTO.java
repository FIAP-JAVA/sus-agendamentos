package br.com.fiap.sus_agendamentos.medico;

public record ListarMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public ListarMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
