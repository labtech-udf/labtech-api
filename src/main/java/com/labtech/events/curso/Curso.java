package com.labtech.events.curso;

import com.labtech.events.constants.SchemaConstants;
import com.labtech.events.constants.Status;
import com.labtech.events.objDesenSus.ObjDesenSus;
import com.labtech.events.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity//mapear
@Table(name="Curso_tb", schema = SchemaConstants.EVENTOS)
public class Curso extends AbstractEntity {

    private String nome;

    private String sigla;

    private String descricao;



}
