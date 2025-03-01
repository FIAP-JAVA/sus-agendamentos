package br.com.fiap.sus_agendamentos.endereco;

public record EnderecoDTO(

        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String complemento,
        String numero

) {
}
