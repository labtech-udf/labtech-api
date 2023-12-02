package br.com.labtech.arquivo;

import br.com.labtech.utils.AbstractEntityDTO;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArquivoDTO extends AbstractEntityDTO {

  String name;
  String type;
  String url;
  Long size;
  String key;
}
