package br.com.fiap.sus_agendamentos.controller.consulta;

import br.com.fiap.sus_agendamentos.domain.consulta.AgendaDeConsultas;
import br.com.fiap.sus_agendamentos.domain.consulta.AgendamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.consulta.DetalhamentoConsultaDTO;
import br.com.fiap.sus_agendamentos.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> agendamentoConsultaDTO;

    @Autowired
    private JacksonTester<DetalhamentoConsultaDTO> detalhamentoConsultaDTO;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamentoConsulta = new DetalhamentoConsultaDTO(null, 2l, 5l, data);
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamentoConsulta);

        var response = mockMvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(agendamentoConsultaDTO.write(
                                        new AgendamentoConsultaDTO(2l, 5l, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalhamentoConsultaDTO.write(dadosDetalhamentoConsulta).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
