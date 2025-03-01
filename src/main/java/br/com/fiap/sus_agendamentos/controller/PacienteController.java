package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.paciente.Paciente;
import br.com.fiap.sus_agendamentos.paciente.PacienteDTO;
import br.com.fiap.sus_agendamentos.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        pacienteRepository.save(new Paciente(pacienteDTO));
    }
}
