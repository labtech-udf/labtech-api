package br.com.labtech.eventoCategoria;

import br.com.labtech.constants.SchemaConstants;
import br.com.labtech.evento.Evento;
import br.com.labtech.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "Categoria_evento_tb", schema = SchemaConstants.EVENTOS)
public class EventoCategoria extends AbstractEntity {

  private String name;

  private String descricao;

  @ManyToMany(mappedBy = "categorias")
  private Set<Evento> eventos = new HashSet<>();

}
