package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendaDeConsultas;
import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.CancelamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.DetalhamentoConsultaDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamentoConsultaDTO) {
        var dto = agendaDeConsultas.agendar(agendamentoConsultaDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        agendaDeConsultas.cancelar(cancelamentoConsultaDTO);
        return ResponseEntity.noContent().build();
    }
}
