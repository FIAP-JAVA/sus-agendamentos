package br.com.fiap.sus_agendamentos.domain.medico;

import br.com.fiap.sus_agendamentos.domain.consulta.Consulta;
import br.com.fiap.sus_agendamentos.domain.endereco.EnderecoDTO;
import br.com.fiap.sus_agendamentos.domain.paciente.Paciente;
import br.com.fiap.sus_agendamentos.domain.paciente.PacienteDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(12, 0);
        var medico = cadastrarMedico("Medico", "medico@susagendamentos.com.br", "123456", Especialidade.ORTOPEDIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.ORTOPEDIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(12, 0);
        var medico = cadastrarMedico("Medico", "medico@susagendamentos.com.br", "123456", Especialidade.ORTOPEDIA);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.ORTOPEDIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(medicoDTO(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(pacienteDTO(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private MedicoDTO medicoDTO(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                enderecoDTO()
        );
    }

    private PacienteDTO pacienteDTO(String nome, String email, String cpf) {
        return new PacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                enderecoDTO()
        );
    }

    private EnderecoDTO enderecoDTO() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
