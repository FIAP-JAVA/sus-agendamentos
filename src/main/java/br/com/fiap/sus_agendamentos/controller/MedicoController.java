package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.medico.Medico;
import br.com.fiap.sus_agendamentos.medico.MedicoDTO;
import br.com.fiap.sus_agendamentos.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO) {
        medicoRepository.save(new Medico(medicoDTO));
    }

}
