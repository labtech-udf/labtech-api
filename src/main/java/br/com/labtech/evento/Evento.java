package br.com.labtech.evento;

import br.com.labtech.arquivo.Arquivo;
import br.com.labtech.constants.SchemaConstants;
import br.com.labtech.enums.Status;
import br.com.labtech.eventoCategoria.EventoCategoria;
import br.com.labtech.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

  @ManyToMany()
  @JoinTable(
    name = "evento_categoria",
    joinColumns = @JoinColumn(name = "evento_id"),
    inverseJoinColumns = @JoinColumn(name = "evento_categoria_id")
  )
  private Set<EventoCategoria> categorias = new HashSet<>();

}
