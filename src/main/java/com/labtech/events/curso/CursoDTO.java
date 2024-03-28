package com.labtech.events.curso;

import com.labtech.events.constants.Status;
import com.labtech.events.objDesenSus.ObjDesenSusDTO;
import com.labtech.events.utils.AbstractEntityDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.*;

import java.util.Set;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CursoDTO extends AbstractEntityDTO {

    private String name;

    private String nameCard;

    private String description;

    private String dateHora;

    private String address;

    private String cor;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Set<ObjDesenSusDTO> ods;

    public String getStatus(){
        return status.getDescricao();
    }

}
