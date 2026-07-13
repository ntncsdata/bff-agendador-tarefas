package com.bff.BffAgendadorTarefas.business.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {

    private String nome;
    private String email;
    private String senha;
}
