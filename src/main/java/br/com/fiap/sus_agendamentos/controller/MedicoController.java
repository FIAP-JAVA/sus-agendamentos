package br.com.fiap.sus_agendamentos.controller;

import br.com.fiap.sus_agendamentos.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Page<ListarMedicoDTO> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(ListarMedicoDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarMedicoDTO atualizarMedicoDTO) {
        var medico = medicoRepository.getReferenceById(atualizarMedicoDTO.id());
        medico.atualizarInformacoes(atualizarMedicoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        medicoRepository.deleteById(id);
    }
}
