package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.medico.ListagemMedicoDTO;
import br.com.fiap.sus_agendamentos.medico.Medico;
import br.com.fiap.sus_agendamentos.medico.MedicoDTO;
import br.com.fiap.sus_agendamentos.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ListagemMedicoDTO> listar() {
        return medicoRepository.findAll().stream().map(ListagemMedicoDTO::new).toList();
    }
}
