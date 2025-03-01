package br.com.fiap.sus_agendamentos.medico;

public record ListagemMedicoDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public ListagemMedicoDTO(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
