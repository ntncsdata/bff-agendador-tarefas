package com.bff.BffAgendadorTarefas.business;

import com.bff.BffAgendadorTarefas.business.dto.in.TarefasDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.bff.BffAgendadorTarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void enviaEmail(TarefasDTOResponse dto) {

        emailClient.enviarEmail(dto);
    }

}
