package br.com.fiap.sus_agendamentos.endereco;

import br.com.fiap.sus_agendamentos.medico.CadastroMedicoDTO;
import br.com.fiap.sus_agendamentos.medico.Especialidade;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String cidade;
    private String numero;
    private String complemento;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.uf = enderecoDTO.uf();
        this.cidade = enderecoDTO.cidade();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
    }

}
