package br.com.labtech.arquivo;

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
@Table(name = "Arquivo_tb", schema = SchemaConstants.EVENTOS)
public class Arquivo extends AbstractEntity {
  String name;
  String type;
  String url;
  Long size;
  String key;
}
