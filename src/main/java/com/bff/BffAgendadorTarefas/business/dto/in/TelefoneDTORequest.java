package com.bff.BffAgendadorTarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TelefoneDTORequest {
    
    private String numero;
    private String ddd;
}
