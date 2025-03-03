package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendaDeConsultas;
import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.CancelamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.DetalhamentoConsultaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamentoConsultaDTO) {
        agendaDeConsultas.agendar(agendamentoConsultaDTO);
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        agendaDeConsultas.cancelar(cancelamentoConsultaDTO);
        return ResponseEntity.noContent().build();
    }
}
