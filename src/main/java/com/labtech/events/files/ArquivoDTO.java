package com.labtech.events.files;


import com.labtech.events.utils.AbstractEntityDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArquivoDTO extends AbstractEntityDTO {
  private String name;
  private String type;
  private String url;
  private Long size;
  private String key;
  private UUID uid;


  public String getUrl() {
    if (getKey() != null) {
      return UrlConstant.getAbsoluteUrl() + "/api/public/arquivo/get/" + getId();
    }
    return null;
  }
}
