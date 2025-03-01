package br.com.fiap.sus_agendamentos.medico;

import br.com.fiap.sus_agendamentos.endereco.Endereco;

public record DetalhamentoMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DetalhamentoMedicoDTO(Medico medico) {
        this(
            medico.getId(),
            medico.getNome(),
            medico.getEmail(),
            medico.getCrm(),
            medico.getTelefone(),
            medico.getEspecialidade(),
            medico.getEndereco()
        );
    }
}
