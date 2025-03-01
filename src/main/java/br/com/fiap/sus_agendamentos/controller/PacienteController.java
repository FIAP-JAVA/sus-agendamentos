package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        pacienteRepository.save(new Paciente(pacienteDTO));
    }

    @GetMapping
    public Page<ListarPacienteDTO> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(ListarPacienteDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarPacienteDTO atualizarPacienteDTO) {
        var paciente = pacienteRepository.getReferenceById(atualizarPacienteDTO.id());
        paciente.atualizarInformacoes(atualizarPacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
