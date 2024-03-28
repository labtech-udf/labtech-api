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

    private String name;

    private String sigla;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String dateHora;

    private String address;

    private String cor;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "curso_ods_tb",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<ObjDesenSus> ods = new  HashSet<>();

}
