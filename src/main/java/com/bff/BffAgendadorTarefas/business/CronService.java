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

    @Value("{usuario.email}")
    private String email;

    @Value("{usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefaProximaHora(){
        String token = login(converterParaDTORequest());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual,
                                                                                                 horaFutura,
                                                                                                 token);
        log.info("Tarefas encontradas" + listaDeTarefas);

        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario" + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaDTORequest(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }


}
