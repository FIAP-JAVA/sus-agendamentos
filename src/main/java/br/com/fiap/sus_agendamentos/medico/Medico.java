package br.com.fiap.sus_agendamentos.medico;

import br.com.fiap.sus_agendamentos.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(MedicoDTO medicoDTO) {
        this.ativo = true;
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.telefone = medicoDTO.telefone();
        this.crm = medicoDTO.crm();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
    }

    public void atualizarInformacoes(AtualizarMedicoDTO atualizarMedicoDTO) {
        if (atualizarMedicoDTO.nome() != null) {
            this.nome = atualizarMedicoDTO.nome();
        }

        if (atualizarMedicoDTO.telefone() != null) {
            this.telefone = atualizarMedicoDTO.telefone();
        }

        if (atualizarMedicoDTO.endereco() != null) {
            this.endereco.atualizarInformacoes(atualizarMedicoDTO.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
