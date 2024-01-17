package br.com.labtech.objDesenSus;

import br.com.labtech.constants.SchemaConstants;
import br.com.labtech.utils.AbstractEntity;
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
@Table(name = "Ods_tb", schema = SchemaConstants.EVENTOS)
public class ObjDesenSus extends AbstractEntity {
//  Objetivo de Desenvolvimento Sustentável

  private String name;

  private String description;

  private boolean status;

}
