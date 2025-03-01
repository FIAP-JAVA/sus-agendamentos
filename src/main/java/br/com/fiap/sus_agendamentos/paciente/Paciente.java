package br.com.fiap.sus_agendamentos.paciente;

import br.com.fiap.sus_agendamentos.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDTO pacienteDTO) {
        this.ativo = true;
        this.nome = pacienteDTO.nome();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Endereco(pacienteDTO.endereco());
    }

    public void atualizarInformacoes(AtualizarPacienteDTO atualizarPacienteDTO) {
        if (atualizarPacienteDTO.nome() != null) {
            this.nome = atualizarPacienteDTO.nome();
        }

        if (atualizarPacienteDTO.telefone() != null) {
            this.telefone = atualizarPacienteDTO.telefone();
        }

        if (atualizarPacienteDTO.endereco() != null) {
            endereco.atualizarInformacoes(atualizarPacienteDTO.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
