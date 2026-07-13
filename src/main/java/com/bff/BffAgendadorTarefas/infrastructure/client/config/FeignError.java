package com.bff.BffAgendadorTarefas.infrastructure.client.config;

import com.bff.BffAgendadorTarefas.infrastructure.exceptions.BusinessException;
import com.bff.BffAgendadorTarefas.infrastructure.exceptions.ConflictExceptions;
import com.bff.BffAgendadorTarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bff.BffAgendadorTarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;


public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response){

        switch (response.status()){
            case 409:
                return new ConflictExceptions("Erro atributo já existente");
            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado");
            case 401:
                return new UnauthorizedException("Erro usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
