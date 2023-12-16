package br.com.labtech.evento;

import br.com.labtech.enums.Status;
import br.com.labtech.arquivo.ArquivoDTO;
import br.com.labtech.eventoCategoria.EventoCategoria;
import br.com.labtech.eventoCategoria.EventoCategoriaDTO;
import br.com.labtech.utils.AbstractEntityDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventoDTO extends AbstractEntityDTO {

  private String name;

  private String nameCard;

  private ArquivoDTO photo;

  private String description;

  private String dateHora;

  private String address;

  private String cor;

  @Enumerated(EnumType.STRING)
  private Status status;

  private Set<EventoCategoriaDTO> categorias;

}
