package br.com.labtech.evento;

import br.com.labtech.constants.SchemaConstants;
import br.com.labtech.generic.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private String nome;

}
