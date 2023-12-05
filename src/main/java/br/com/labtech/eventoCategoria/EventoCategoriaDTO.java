package br.com.labtech.eventoCategoria;

import br.com.labtech.arquivo.ArquivoDTO;
import br.com.labtech.enums.Status;
import br.com.labtech.evento.EventoDTO;
import br.com.labtech.utils.AbstractEntityDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventoCategoriaDTO extends AbstractEntityDTO {

    private String name;

    private String descricao;

    private Set<EventoDTO> eventos;


}
