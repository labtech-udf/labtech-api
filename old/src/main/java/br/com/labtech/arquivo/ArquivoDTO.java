package br.com.labtech.arquivo;

import br.com.labtech.utils.AbstractEntityDTO;
import br.com.labtech.utils.UrlConstant;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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

  public String getUrl() {
    if (url != null) {
      return UrlConstant.getAbsoluteUrl() + "/" + url + "/download";
    } else {
      return UrlConstant.getAbsoluteUrl() + "/" + getId() + "/download";
    }
  }
}
