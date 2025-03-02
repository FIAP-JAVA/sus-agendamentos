package br.com.fiap.sus_agendamentos.infra;

import org.springframework.validation.FieldError;

public record ErroValidacaoDTO(String campo, String mensagem) {

    public ErroValidacaoDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
