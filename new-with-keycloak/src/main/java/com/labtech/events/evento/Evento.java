package com.labtech.events.evento;

import com.labtech.events.constants.SchemaConstants;
import com.labtech.events.constants.Status;
import com.labtech.events.files.Arquivo;
import com.labtech.events.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Evento_tb", schema = SchemaConstants.EVENTOS)
public class Evento extends AbstractEntity {

  private String name;

  private String nameCard;

  @ManyToOne
  private Arquivo photo;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String dateHora;

  private String address;

  private String cor;

  @Enumerated(EnumType.STRING)
  private Status status;

//  @ManyToMany()
//  @JoinTable(
//    name = "evento_categoria",
//    joinColumns = @JoinColumn(name = "evento_id"),
//    inverseJoinColumns = @JoinColumn(name = "evento_categoria_id")
//  )
//  private Set<EventoCategoria> categorias = new HashSet<>();

//  @ManyToMany
//  @JoinTable(
//    name = "evento_ods",
//    joinColumns = @JoinColumn(name = "evento_id"),
//    inverseJoinColumns = @JoinColumn(name = "ods_id")
//  )
//  private Set<ObjDesenSus> ods = new HashSet<>();

}
