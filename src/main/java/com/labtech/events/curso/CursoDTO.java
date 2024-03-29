package com.labtech.events.curso;

import com.labtech.events.constants.Status;
import com.labtech.events.objDesenSus.ObjDesenSusDTO;
import com.labtech.events.utils.AbstractEntityDTO;
import jakarta.persistence.*;

import lombok.*;

import java.util.Set;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CursoDTO extends AbstractEntityDTO {

    private String nome;

    private String sigla;

    private String descricao;


}
