package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.DetalhamentoConsultaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamentoConsultaDTO) {
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null, null, null, null));
    }
}
