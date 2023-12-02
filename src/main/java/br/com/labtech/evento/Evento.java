package br.com.labtech.evento;

import br.com.labtech.arquivo.Arquivo;
import br.com.labtech.constants.SchemaConstants;
import br.com.labtech.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

  private String description;

  private LocalDateTime dateHora;

  private String address;

  private String cor;

}
