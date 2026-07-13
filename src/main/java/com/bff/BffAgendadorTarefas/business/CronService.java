package com.bff.BffAgendadorTarefas.business;


import com.bff.BffAgendadorTarefas.business.dto.in.LoginDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.bff.BffAgendadorTarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    public final TarefasService tarefasService;
    public final EmailService emailService;
    public final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefaProximaHora() {
        log.info("Iniciada a busca de tarefas");

        String token;
        try {
            token = usuarioService.loginUsuario(
                    LoginDTORequest.builder().email(email).senha(senha).build());
        } catch (Exception e) {
            log.error("Falha no login do usuário técnico do cron", e);
            return;
        }

        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = horaAtual.plusHours(1);

        List<TarefasDTOResponse> tarefas;
        try {
            tarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);
        } catch (Exception e) {
            log.error("Falha ao buscar tarefas do período", e);
            return;
        }

        log.info("Tarefas encontradas: {}", tarefas.size());

        for (TarefasDTOResponse tarefa : tarefas) {
            if (tarefa.getStatusNotificacaoEnum() != StatusNotificacaoEnum.PENDENTE) {
                continue;
            }
            try {
                emailService.enviaEmail(tarefa);
                tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
                log.info("Email enviado para {}", tarefa.getEmailUsuario());
            } catch (Exception e) {
                log.error("Falha ao notificar a tarefa {}", tarefa.getId(), e);
            }
        }

        log.info("Finalizada a busca e notificação de tarefas");
    }


}
