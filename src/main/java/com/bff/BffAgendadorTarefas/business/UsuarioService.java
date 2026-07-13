package com.bff.BffAgendadorTarefas.business;


import com.bff.BffAgendadorTarefas.business.dto.in.EnderecoDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.in.LoginDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.in.TelefoneDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.in.UsuarioDTORequest;
import com.bff.BffAgendadorTarefas.business.dto.out.EnderecoDTOResponse;
import com.bff.BffAgendadorTarefas.business.dto.out.TelefoneDTOResponse;
import com.bff.BffAgendadorTarefas.business.dto.out.UsuarioDTOResponse;
import com.bff.BffAgendadorTarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTORequest) {

        return client.salvaUsuario(usuarioDTORequest);
    }


    public String loginUsuario(LoginDTORequest dto){

        return client.login(dto);
    }


    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {

       return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletaUsuarioPorEmail(String email, String token) {

        client.deletaUsuarioPorEmail(email, token);
    }


    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {

        return client.atualizaDadosUsuario(dto, token);
    }


    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTORequest, String token) {

        return client.atualizaEndereco(enderecoDTORequest, idEndereco, token);
    }


    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {

            return client.atualizaTelefone(dto, idTelefone, token);
    }


    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {

        return client.cadastraEndereco(dto, token);
    }


    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto) {

        return client.cadastraTelefone(dto, token);
    }



}
