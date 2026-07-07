package com.bff.BffAgendadorTarefas.business;

import com.bff.BffAgendadorTarefas.business.dto.in.TarefasDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.bff.BffAgendadorTarefas.business.enums.StatusNotificacaoEnum;
import com.bff.BffAgendadorTarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {

        return tarefasClient.gravarTarefas(dto, token);
    }


    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token){

        return tarefasClient.buscarListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token){

        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletarTarefasPorId(String id, String token) {
        tarefasClient.deletarTarefasPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {

        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token){

        return tarefasClient.updateTarefas(dto, id, token);
    }


}
