package br.com.labtech.evento;

import br.com.labtech.arquivo.Arquivo;
import br.com.labtech.arquivo.ArquivoDTO;
import br.com.labtech.utils.AbstractEntityDTO;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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

    private LocalDateTime dateHora;

    private String address;

    private String cor;
}
